package lk.eclk.locationservice.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.work.*

import lk.eclk.locationservice.R
import lk.eclk.locationservice.internal.AuthState
import lk.eclk.locationservice.workers.RefreshAccessTokenWorker
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.util.concurrent.TimeUnit

class SplashScreenFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private lateinit var viewModel: SplashScreenViewModel
    private val viewModelFactory: SplashScreenViewModelFactory by instance()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SplashScreenViewModel::class.java)
        navController = Navigation.findNavController(view)
        bindUI()
    }

    private fun bindUI() {
        viewModel.authState.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            when (it) {
                AuthState.NEED_LOGIN -> navController.navigate(R.id.action_splashScreenFragment_to_signInFragment)
                AuthState.LOGGED_IN -> {
                    startPeriodicRefreshTokenTask()
                    navController.navigate(R.id.action_splashScreenFragment_to_homeFragment)
                }
            }
        })
    }

    private fun startPeriodicRefreshTokenTask() {
        val uniqueWorkName = "location-service-app-392:refresh-token-work"

        val workConstraints = Constraints
            .Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicRefreshTokenWork =
            PeriodicWorkRequestBuilder<RefreshAccessTokenWorker>(4, TimeUnit.MINUTES)
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
