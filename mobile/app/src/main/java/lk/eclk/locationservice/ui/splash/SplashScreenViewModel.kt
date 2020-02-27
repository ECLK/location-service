package lk.eclk.locationservice.ui.splash

import androidx.lifecycle.ViewModel
import lk.eclk.locationservice.data.repository.Repository

class SplashScreenViewModel(private val repository: Repository) : ViewModel() {
    val authState by lazy { repository.getAuthState() }
}
