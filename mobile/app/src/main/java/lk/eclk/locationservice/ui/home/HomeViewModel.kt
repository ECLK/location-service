package lk.eclk.locationservice.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.eclk.locationservice.data.repository.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {
    fun searchLocations(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            repository.searchLocations(query)
        }
    }
}
