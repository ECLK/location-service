package lk.eclk.locationservice.data.remote.datasources

import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.AuthState

interface AuthenticationNetworkDataSource {
    suspend fun signIn(username: String, password: String): Pair<TokenResponse, AuthState>
}