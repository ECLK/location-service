package lk.eclk.locationservice.ui.signin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lk.eclk.locationservice.data.repository.Repository

class SignInViewModelFactory(private val repository: Repository,private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignInViewModel(repository,context) as T
    }
}