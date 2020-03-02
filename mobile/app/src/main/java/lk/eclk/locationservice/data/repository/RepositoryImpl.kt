package lk.eclk.locationservice.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lk.eclk.locationservice.data.proviers.JWTProvider
import lk.eclk.locationservice.data.remote.datasources.LocationServiceApiNetworkDataSource
import lk.eclk.locationservice.internal.ResponseStates
import lk.eclk.locationservice.internal.AuthState

class RepositoryImpl(
    private val jwtProvider: JWTProvider,
    private val locationServiceApiNetworkDataSource: LocationServiceApiNetworkDataSource
) : Repository {

    init {
        jwtProvider.apply {
            authState.observeForever {
                GlobalScope.launch(Dispatchers.IO) {
                    // there should handle auth state's data
                    // eg: fetching user data or remove caches
                }
            }
        }
    }

    override fun getAuthState(): LiveData<AuthState> = jwtProvider.authState

    override suspend fun signIn(username: String, password: String): ResponseStates {
        return withContext(Dispatchers.IO) {
            var pair = locationServiceApiNetworkDataSource.signIn(username, password)
            if (pair.first != null) {
                jwtProvider.setTokens(pair.first!!)
            }
            return@withContext pair.second
        }
    }

    override fun signOut() = jwtProvider.deleteTokens()
}