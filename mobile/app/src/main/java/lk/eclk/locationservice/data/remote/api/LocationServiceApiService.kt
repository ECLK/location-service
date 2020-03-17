package lk.eclk.locationservice.data.remote.api

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import lk.eclk.locationservice.R
import lk.eclk.locationservice.data.remote.interceptors.AuthenticityInterceptor
import lk.eclk.locationservice.data.remote.interceptors.AuthorizationInterceptor
import lk.eclk.locationservice.data.remote.interceptors.ConnectivityInterceptor
import lk.eclk.locationservice.models.Location
import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.models.MediaItem
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface LocationServiceApiService {

    @FormUrlEncoded
    @POST("token/")
    fun signIn(
        @Field("username") username: String,
        @Field("password") password: String
    ): Deferred<TokenResponse>

    @FormUrlEncoded
    @POST("token/refresh/")
    fun refreshToken(
        @Field("refresh") token: String?
    ): Deferred<TokenResponse>

    @GET("search/")
    fun searchLocations(
        @Query("search") text: String?
    ): Deferred<List<Location>>

    @Multipart
    @POST("img/")
    fun uploadImages(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part media: MultipartBody.Part,
        @Part("file_type") fileType: RequestBody,
        @Part("latitude") latitude: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part("location") location: RequestBody
    ):Deferred<MediaItem>


    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor,
            authenticityInterceptor: AuthenticityInterceptor,
            authorizationInterceptor: AuthorizationInterceptor,
            context: Context
        ): LocationServiceApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(authenticityInterceptor)
                .addInterceptor(authorizationInterceptor)
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