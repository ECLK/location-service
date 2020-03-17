package lk.eclk.locationservice.ui.locationdetailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.eclk.locationservice.data.repository.Repository
import lk.eclk.locationservice.models.Location
import lk.eclk.locationservice.models.MediaItem

class LocationDetailedViewModel(private val repository: Repository) : ViewModel() {
    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location
    private val _saved = MutableLiveData<Boolean>(false)
    val saved: LiveData<Boolean> = _saved

    fun setLocation(location: Location) {
        getLocation(location.code)
        _location.postValue(location)
    }

    fun insertLocation() {
        repository.insertLocation(location.value!!)
        _saved.postValue(true)
    }

    private fun getLocation(code: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val res = repository.getLocation(code)
            res?.let { _location.postValue(it) }
            _saved.postValue(res != null)
        }
    }

    fun insertMediaItem(item: MediaItem) {
        repository.uploadMediaItem(item)
        repository.insertMediaItem(item)
    }
}