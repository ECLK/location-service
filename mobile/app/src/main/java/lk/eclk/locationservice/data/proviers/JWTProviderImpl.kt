package lk.eclk.locationservice.data.proviers

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.AuthState

private const val ACCESS_TOKEN = "access_token"
private const val REFRESH_TOKEN = "access_token"

class JWTProviderImpl(context: Context) : PreferenceProvider(context), JWTProvider {

    // any view model can observe auth state using this live data
    override val authState: LiveData<AuthState> get() = _authState
    private val _authState by lazy { MutableLiveData<AuthState>() }

    init {
        setAuthState(getAccessToken())
    }

    override fun getAccessToken(): String? {
        return preference.getString(ACCESS_TOKEN, null)
    }

    override fun getRefreshToken(): String? {
        return preference.getString(REFRESH_TOKEN, null)
    }

    override fun setTokens(tokenResponse: TokenResponse): Boolean {
        setAuthState(tokenResponse.accessToken)
        return preference.edit()
            .putString(ACCESS_TOKEN, tokenResponse.accessToken)
            .putString(REFRESH_TOKEN, tokenResponse.refreshToken)
            .commit()
    }

    override fun deleteTokens(): Boolean {
        setAuthState(null)
        return preference.edit()
            .remove(ACCESS_TOKEN)
            .remove(REFRESH_TOKEN)
            .commit()
    }

    private fun setAuthState(jwt: String?) {
        if (jwt.isNullOrEmpty()) _authState.postValue(AuthState.NEED_LOGIN)
        if (!jwt.isNullOrEmpty()) _authState.postValue(AuthState.LOGGED_IN)
    }
}