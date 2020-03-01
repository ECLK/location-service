package lk.eclk.locationservice.data.remote.datasources

import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.AuthResponseState

interface AuthenticationNetworkDataSource {
    suspend fun signIn(username: String, password: String): Pair<TokenResponse?, AuthResponseState>
}