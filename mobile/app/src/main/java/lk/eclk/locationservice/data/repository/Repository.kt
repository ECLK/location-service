package lk.eclk.locationservice.data.repository

import androidx.lifecycle.LiveData
import lk.eclk.locationservice.internal.AuthResponseState
import lk.eclk.locationservice.internal.AuthState

interface Repository {

    //repository for handle all data flows inside the android app
    fun getAuthState(): LiveData<AuthState>

    suspend fun signIn(username: String, password: String): AuthResponseState
    fun signOut(): Boolean
}