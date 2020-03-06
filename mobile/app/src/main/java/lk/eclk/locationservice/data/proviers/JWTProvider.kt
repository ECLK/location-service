package lk.eclk.locationservice.data.proviers

import androidx.lifecycle.LiveData
import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.AuthState

interface JWTProvider {
    val authState: LiveData<AuthState>
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun setTokens(tokenResponse: TokenResponse): Boolean
    fun setAccessToken(tokenResponse: TokenResponse): Boolean
    fun deleteTokens(): Boolean
}