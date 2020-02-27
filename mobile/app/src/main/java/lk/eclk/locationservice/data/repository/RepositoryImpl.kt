package lk.eclk.locationservice.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lk.eclk.locationservice.data.proviers.JWTProvider
import lk.eclk.locationservice.data.remote.datasources.AuthenticationNetworkDataSource
import lk.eclk.locationservice.internal.AuthResponseState
import lk.eclk.locationservice.internal.AuthState

class RepositoryImpl(
    private val jwtProvider: JWTProvider,
    private val authenticationNetworkDataSource: AuthenticationNetworkDataSource
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

    override suspend fun signIn(username: String, password: String): AuthResponseState {
        return withContext(Dispatchers.IO) {
            var pair = authenticationNetworkDataSource.signIn(username, password)
            if (pair.first != null) {
                jwtProvider.setTokens(pair.first!!)
            }
            return@withContext pair.second
        }
    }

    override fun signOut() = jwtProvider.deleteTokens()
}