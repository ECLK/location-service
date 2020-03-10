package lk.eclk.locationservice.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lk.eclk.locationservice.data.repository.Repository

class SearchViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}