package lk.eclk.locationservice.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.eclk.locationservice.data.proviers.JWTProvider
import lk.eclk.locationservice.internal.AuthState

class RepositoryImpl(
    private val jwtProvider: JWTProvider
) : Repository {

    init {
        jwtProvider.apply {
            authState.observeForever {
                GlobalScope.launch(Dispatchers.IO) {
                    // there should handle auth state's data
                    // eg: fetching user data
                }
            }
        }
    }

    override fun getAuthState(): LiveData<AuthState> = jwtProvider.authState


}