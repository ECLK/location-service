package lk.eclk.locationservice.data.remote.api

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import lk.eclk.locationservice.R
import lk.eclk.locationservice.data.remote.interceptors.AuthenticityInterceptor
import lk.eclk.locationservice.data.remote.interceptors.ConnectivityInterceptor
import lk.eclk.locationservice.data.remote.responses.TokenResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface LocationServiceApiService {

    @FormUrlEncoded
    @POST("/api/token/")
    fun signIn(
        @Field("username") username: String,
        @Field("password") password: String
    ): Deferred<TokenResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor,
            authenticityInterceptor: AuthenticityInterceptor,
            context: Context
        ): LocationServiceApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(authenticityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(context.applicationContext.getString(R.string.backendBaseUrl))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LocationServiceApiService::class.java)
        }
    }

}