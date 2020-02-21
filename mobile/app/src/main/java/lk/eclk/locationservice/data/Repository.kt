package lk.eclk.locationservice.data

import androidx.lifecycle.LiveData
import lk.eclk.locationservice.internal.AuthState

interface Repository {

    //repository for handle all data flows inside the android app
    fun getAuthState(): LiveData<AuthState>
}