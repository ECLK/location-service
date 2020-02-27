package lk.eclk.locationservice.data.remote.interceptors

import android.util.Log
import lk.eclk.locationservice.internal.NoAuthenticityException
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticityInterceptorImpl : AuthenticityInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        Log.d("API response", "Code : ${response.code()}")
        if (response.code() == 401) {
            throw NoAuthenticityException()
        }
        return response
    }
}