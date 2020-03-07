package lk.eclk.locationservice.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.eclk.locationservice.data.repository.Repository
import lk.eclk.locationservice.internal.eventexecutor.LiveMessageEvent
import lk.eclk.locationservice.models.Location

class HomeViewModel(private val repository: Repository) : ViewModel() {

    val liveMessageEvent = LiveMessageEvent<HomeMessageEvents>()
    private val _searching: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val searching: LiveData<Boolean> = _searching

    fun searchLocations(query: String?) {
        _searching.postValue(true)
        GlobalScope.launch(Dispatchers.IO) {
            val response = repository.searchLocations(query)
            _searching.postValue(false)
            response?.let {
                GlobalScope.launch(Dispatchers.Main) {
                    liveMessageEvent.sendEvent {
                        initRecyclerView(
                            response.toLocationListItems()
                        )
                    }
                }
            }
        }
    }

    private fun List<Location>.toLocationListItems() = this.map { LocationListItem(it) }
}
