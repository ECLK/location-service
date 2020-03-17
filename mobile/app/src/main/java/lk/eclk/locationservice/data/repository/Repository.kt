package lk.eclk.locationservice.data.repository

import androidx.lifecycle.LiveData
import lk.eclk.locationservice.internal.ResponseStates
import lk.eclk.locationservice.internal.AuthState
import lk.eclk.locationservice.models.Location
import lk.eclk.locationservice.models.MediaItem

interface Repository {

    //repository for handle all data flows inside the android app
    fun getAuthState(): LiveData<AuthState>

    suspend fun signIn(username: String, password: String): ResponseStates
    fun signOut(): Boolean
    suspend fun refreshAccessToken(): ResponseStates

    suspend fun searchLocations(query: String?): List<Location>?

    fun insertLocation(location: Location)
    fun getLocation(code: String): Location?
    fun getLocations(): LiveData<List<Location>>

    fun insertMediaItem(item: MediaItem)
    fun uploadMediaItem(item: MediaItem)
}