package lk.eclk.locationservice.data.remote.datasources

import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.ResponseStates
import lk.eclk.locationservice.models.Location
import lk.eclk.locationservice.models.MediaItem

interface LocationServiceApiNetworkDataSource {

    suspend fun signIn(username: String, password: String): Pair<TokenResponse?, ResponseStates>
    suspend fun refreshAccessToken(token:String?): Pair<TokenResponse?, ResponseStates>
    suspend fun searchLocations(query: String?):List<Location>?
    suspend fun uploadImage(mediaItem: MediaItem):ResponseStates
}