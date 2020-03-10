package lk.eclk.locationservice.ui.home

import androidx.lifecycle.ViewModel
import lk.eclk.locationservice.data.repository.Repository
import lk.eclk.locationservice.internal.lazyDeferred

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val locations by lazyDeferred { repository.getLocations() }
}
