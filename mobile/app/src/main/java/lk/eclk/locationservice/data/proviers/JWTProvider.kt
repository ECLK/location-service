package lk.eclk.locationservice.data.proviers

import androidx.lifecycle.LiveData
import lk.eclk.locationservice.internal.AuthState

interface JWTProvider {
    val authState: LiveData<AuthState>
    fun getJWT(): String?
    fun setJWT(token: String): Boolean
    fun unsetJWT(): Boolean
}