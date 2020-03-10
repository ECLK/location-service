package lk.eclk.locationservice.ui.locationdetailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.eclk.locationservice.data.repository.Repository
import lk.eclk.locationservice.models.Location

class LocationDetailedViewModel(private val repository: Repository) : ViewModel() {
    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location
    private val _saved = MutableLiveData<Boolean>(false)
    val saved: LiveData<Boolean> = _saved

    fun setLocation(location: Location) {
        getLocation(location.code)
        if (_location.value == null)
            _location.postValue(location)
    }

    fun insertLocation() {
        GlobalScope.launch(Dispatchers.IO) {
            repository.insertLocation(location.value!!)
            _saved.postValue(true)
        }
    }

    private fun getLocation(code: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val res = repository.getLocation(code)
            _location.postValue(res)
            _saved.postValue(res != null)
        }
    }
}