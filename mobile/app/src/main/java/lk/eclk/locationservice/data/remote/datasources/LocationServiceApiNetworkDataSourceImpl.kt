package lk.eclk.locationservice.data.remote.datasources

import android.util.Log
import lk.eclk.locationservice.data.remote.api.LocationServiceApiService
import lk.eclk.locationservice.data.remote.responses.LocationResponse
import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.ResponseStates
import lk.eclk.locationservice.internal.NoAuthenticityException
import lk.eclk.locationservice.internal.NoConnectivityException

class LocationServiceApiNetworkDataSourceImpl(private val apiService: LocationServiceApiService) :
    LocationServiceApiNetworkDataSource {

    override suspend fun signIn(
        username: String,
        password: String
    ): Pair<TokenResponse?, ResponseStates> {
        return try {
            val response = apiService.signIn(username, password).await()
            Pair(response, ResponseStates.AUTHENTICATED)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
            Pair(null, ResponseStates.NO_CONNECTIVITY)
        } catch (e: NoAuthenticityException) {
            Log.e("Authentication", "401.", e)
            Pair(null, ResponseStates.UNAUTHENTICATED)
        } catch (e: Exception) {
            Log.e("Other error", "other error,${e.message}", e)
            Pair(null, ResponseStates.ERROR)
        }
    }

    override suspend fun searchLocations(query: String): Pair<LocationResponse?, ResponseStates> {
        return try {
            val response = apiService.searchLocations(query).await()
            return Pair(response, ResponseStates.OK)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
            Pair(null, ResponseStates.NO_CONNECTIVITY)
        } catch (e: NoAuthenticityException) {
            Log.e("Authentication", "401.", e)
            Pair(null, ResponseStates.UNAUTHENTICATED)
        } catch (e: Exception) {
            Log.e("Other error", "other error,${e.message}", e)
            Pair(null, ResponseStates.ERROR)
        }
    }
}