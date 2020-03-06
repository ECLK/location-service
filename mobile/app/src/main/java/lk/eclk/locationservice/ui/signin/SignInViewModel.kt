package lk.eclk.locationservice.ui.signin

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.eclk.locationservice.R
import lk.eclk.locationservice.data.repository.Repository
import lk.eclk.locationservice.internal.ResponseStates
import lk.eclk.locationservice.internal.eventexecutor.LiveMessageEvent
import lk.eclk.locationservice.internal.eventexecutor.MessageEvents
import lk.eclk.locationservice.workers.RefreshTokenWorker
import java.util.concurrent.TimeUnit

class SignInViewModel(private val repository: Repository, private val context: Context) :
    ViewModel() {

    val signingIn: LiveData<Boolean> get() = _signingIn
    private val _signingIn = MutableLiveData<Boolean>(false)

    //for send events to fragment
    val liveMessageEvent = LiveMessageEvent<MessageEvents>()

    fun signIn(username: String, password: String) {
        _signingIn.postValue(true)
        GlobalScope.launch(Dispatchers.IO) {
            when (repository.signIn(username, password)) {
                ResponseStates.AUTHENTICATED -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        startPeriodicRefreshTokenTask()
                        liveMessageEvent.sendEvent { showSnackBar("Authenticated successfully!") }
                        liveMessageEvent.sendEvent { navigate(R.id.action_signInFragment_to_homeFragment) }
                    }
                }
                ResponseStates.UNAUTHENTICATED -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        liveMessageEvent.sendEvent {
                            showSnackBar(
                                "Username or password incorrect!"
                            )
                        }
                    }
                }
                ResponseStates.NO_CONNECTIVITY -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        liveMessageEvent.sendEvent {
                            showSnackBar(
                                "You have no connection to internet!"
                            )
                        }
                    }
                }
                ResponseStates.ERROR -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        liveMessageEvent.sendEvent {
                            showSnackBar(
                                "Something went wrong!"
                            )
                        }
                    }
                }
            }
        }
        _signingIn.postValue(false)
    }

    private fun startPeriodicRefreshTokenTask() {
        val uniqueWorkName = "location-service-app-392:refresh-token-work"

        val workConstraints = Constraints
            .Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicRefreshTokenWork =
            PeriodicWorkRequestBuilder<RefreshTokenWorker>(4, TimeUnit.MINUTES)
                .setConstraints(workConstraints)
                .build()

        WorkManager.getInstance(context!!)
            .enqueueUniquePeriodicWork(
                uniqueWorkName,
                ExistingPeriodicWorkPolicy.REPLACE,
                periodicRefreshTokenWork
            )
    }
}
