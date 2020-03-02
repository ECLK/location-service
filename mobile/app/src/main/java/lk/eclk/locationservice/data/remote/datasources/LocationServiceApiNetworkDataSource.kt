package lk.eclk.locationservice.data.remote.datasources

import lk.eclk.locationservice.data.remote.responses.LocationResponse
import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.ResponseStates

interface LocationServiceApiNetworkDataSource {
    suspend fun signIn(username: String, password: String): Pair<TokenResponse?, ResponseStates>
    suspend fun searchLocations(query: String): Pair<LocationResponse?,ResponseStates>
}