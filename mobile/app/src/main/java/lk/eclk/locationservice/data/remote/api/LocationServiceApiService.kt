package lk.eclk.locationservice.data.remote.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import lk.eclk.locationservice.data.remote.ConnectivityInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface LocationServiceApiService {

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): LocationServiceApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
//                .baseUrl("http://10.0.2.2:3000/")
//                .baseUrl("https://locationservice.eclk.lk/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LocationServiceApiService::class.java)
        }
    }

}