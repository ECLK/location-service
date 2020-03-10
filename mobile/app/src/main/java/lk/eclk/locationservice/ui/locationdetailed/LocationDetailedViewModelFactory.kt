package lk.eclk.locationservice.ui.locationdetailed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lk.eclk.locationservice.data.repository.Repository

class LocationDetailedViewModelFactory(private val repository: Repository):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationDetailedViewModel(repository) as T
    }
}