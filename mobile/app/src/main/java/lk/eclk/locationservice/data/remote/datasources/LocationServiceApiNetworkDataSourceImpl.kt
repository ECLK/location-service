package lk.eclk.locationservice.data.remote.datasources

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.eclk.locationservice.data.remote.api.LocationServiceApiService
import lk.eclk.locationservice.data.remote.responses.LocationResponse
import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.ResponseStates
import lk.eclk.locationservice.internal.NoAuthenticityException
import lk.eclk.locationservice.internal.NoConnectivityException
import lk.eclk.locationservice.models.Location

class LocationServiceApiNetworkDataSourceImpl(private val apiService: LocationServiceApiService) :
    LocationServiceApiNetworkDataSource {
    override val locationsResponse: LiveData<LocationResponse> get() = _locationsResponse
    private val _locationsResponse by lazy { MutableLiveData<LocationResponse>() }

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

    override suspend fun searchLocations(query: String?) {
        try {
            val response = apiService.searchLocations(query).await()
            _locationsResponse.postValue(response)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        } catch (e: NoAuthenticityException) {
            Log.e("Authentication", "401.", e)
        } catch (e: Exception) {
            Log.e("Other error", "other error,${e.message}", e)
        }
    }
}