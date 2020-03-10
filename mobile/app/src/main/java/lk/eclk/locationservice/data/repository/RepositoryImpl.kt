package lk.eclk.locationservice.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lk.eclk.locationservice.data.dao.LocationDao
import lk.eclk.locationservice.data.proviers.JWTProvider
import lk.eclk.locationservice.data.remote.datasources.LocationServiceApiNetworkDataSource
import lk.eclk.locationservice.internal.ResponseStates
import lk.eclk.locationservice.internal.AuthState
import lk.eclk.locationservice.models.Location

class RepositoryImpl(
    private val jwtProvider: JWTProvider,
    private val locationServiceApiNetworkDataSource: LocationServiceApiNetworkDataSource,
    private val locationDao: LocationDao
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
            val pair = locationServiceApiNetworkDataSource.signIn(username, password)
            if (pair.first != null) {
                jwtProvider.setTokens(pair.first!!)
            }
            return@withContext pair.second
        }
    }

    override fun signOut() = jwtProvider.deleteTokens()
    override suspend fun refreshAccessToken(): ResponseStates {
        return withContext(Dispatchers.IO) {
            var pair =
                locationServiceApiNetworkDataSource.refreshAccessToken(jwtProvider.getRefreshToken())
            if (pair.first != null) {
                jwtProvider.setAccessToken(pair.first!!)
            }
            return@withContext pair.second
        }
    }

    override suspend fun searchLocations(query: String?): List<Location>? {
        return withContext(Dispatchers.IO) {
            return@withContext locationServiceApiNetworkDataSource.searchLocations(query)
        }
    }

    override fun insertLocation(location: Location) {
        locationDao.upsert(location)
    }

    override fun getLocation(code: String): Location? {
        return locationDao.getLocation(code)
    }

    override fun getLocations() = locationDao.getLocations()
}