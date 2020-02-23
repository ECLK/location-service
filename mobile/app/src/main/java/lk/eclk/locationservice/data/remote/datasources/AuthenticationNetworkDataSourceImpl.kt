package lk.eclk.locationservice.data.remote.datasources

import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.AuthState

class AuthenticationNetworkDataSourceImpl : AuthenticationNetworkDataSource {
    override suspend fun signIn(
        username: String,
        password: String
    ): Pair<TokenResponse, AuthState> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}