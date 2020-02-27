package lk.eclk.locationservice.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lk.eclk.locationservice.data.repository.Repository

class HomeViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}