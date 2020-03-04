package lk.eclk.locationservice.data.remote.interceptors

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.eclk.locationservice.data.proviers.JWTProvider
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptorImpl(private val jwtProvider: JWTProvider) :
    AuthorizationInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        jwtProvider.getAccessToken().let {
            request = request
                .newBuilder()
                .header("Authorization", "Bearer ${jwtProvider.getAccessToken()}")
                .build()
        }
        return chain.proceed(request)
    }
}