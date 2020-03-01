package lk.eclk.locationservice.data.remote.datasources

import android.util.Log
import lk.eclk.locationservice.data.remote.api.LocationServiceApiService
import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.AuthResponseState
import lk.eclk.locationservice.internal.NoAuthenticityException
import lk.eclk.locationservice.internal.NoConnectivityException

class AuthenticationNetworkDataSourceImpl(private val apiService: LocationServiceApiService) :
    AuthenticationNetworkDataSource {
    override suspend fun signIn(
        username: String,
        password: String
    ): Pair<TokenResponse?, AuthResponseState> {
        return try {
            val response = apiService.signIn(username, password).await()
            Pair(response, AuthResponseState.AUTHENTICATED)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
            Pair(null, AuthResponseState.NO_CONNCECTIVITY)
        } catch (e: NoAuthenticityException) {
            Log.e("Authentication", "401.", e)
            Pair(null, AuthResponseState.UNAUTHENTICATED)
        } catch (e: Exception) {
            Log.e("Other error", "other error,${e.message}", e)
            Pair(null, AuthResponseState.ERROR)
        }
    }
}