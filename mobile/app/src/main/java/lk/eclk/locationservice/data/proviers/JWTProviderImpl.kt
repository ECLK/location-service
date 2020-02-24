package lk.eclk.locationservice.data.proviers

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import lk.eclk.locationservice.internal.AuthState

private const val JWT_TOKEN = "jwt_token"

class JWTProviderImpl(context: Context) : PreferenceProvider(context), JWTProvider {

    // any view model can observe auth state using this live data
    override val authState: LiveData<AuthState> get() = _authState
    private val _authState by lazy { MutableLiveData<AuthState>() }

    init {
        setAuthState(getJWT())
    }

    override fun getJWT(): String? {
        return preference.getString(JWT_TOKEN, null)
    }

    override fun setJWT(token: String): Boolean {
        setAuthState(token)
        return preference.edit().putString(JWT_TOKEN, token).commit()
    }

    override fun unsetJWT(): Boolean {
        setAuthState(null)
        return preference.edit().remove(JWT_TOKEN).commit()
    }

    private fun setAuthState(jwt: String?) {
        if (jwt.isNullOrEmpty()) _authState.postValue(AuthState.NEED_LOGIN)
        if (!jwt.isNullOrEmpty()) _authState.postValue(AuthState.LOGGED_IN)
    }
}