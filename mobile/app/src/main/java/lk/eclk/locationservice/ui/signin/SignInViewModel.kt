package lk.eclk.locationservice.ui.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.eclk.locationservice.R
import lk.eclk.locationservice.data.repository.Repository
import lk.eclk.locationservice.internal.AuthResponseState
import lk.eclk.locationservice.internal.eventexecutor.LiveMessageEvent
import lk.eclk.locationservice.internal.eventexecutor.MessageEvents

class SignInViewModel(private val repository: Repository) : ViewModel() {

    val signingIn: LiveData<Boolean> get() = _signingIn
    private val _signingIn = MutableLiveData<Boolean>(false)

    //for send events to fragment
    val liveMessageEvent = LiveMessageEvent<MessageEvents>()

    fun signIn(username: String, password: String) {
        _signingIn.postValue(true)
        GlobalScope.launch(Dispatchers.IO) {
            when (repository.signIn(username, password)) {
                AuthResponseState.AUTHENTICATED -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        liveMessageEvent.sendEvent { showSnackBar("Authenticated successfully!") }
                        liveMessageEvent.sendEvent { navigate(R.id.action_signInFragment_to_homeFragment) }
                    }
                }
                AuthResponseState.UNAUTHENTICATED -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        liveMessageEvent.sendEvent {
                            showSnackBar(
                                "Username or password incorrect!"
                            )
                        }
                    }
                }
                AuthResponseState.NO_CONNCECTIVITY -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        liveMessageEvent.sendEvent {
                            showSnackBar(
                                "You have no connection to internet!"
                            )
                        }
                    }
                }
                AuthResponseState.ERROR -> {
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
}
