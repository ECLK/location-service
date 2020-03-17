package lk.eclk.locationservice.data.remote.datasources

import android.util.Log
import lk.eclk.locationservice.data.remote.api.LocationServiceApiService
import lk.eclk.locationservice.data.remote.responses.TokenResponse
import lk.eclk.locationservice.internal.ResponseStates
import lk.eclk.locationservice.internal.NoAuthenticityException
import lk.eclk.locationservice.internal.NoConnectivityException
import lk.eclk.locationservice.models.Location
import lk.eclk.locationservice.models.MediaItem
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileNotFoundException

class LocationServiceApiNetworkDataSourceImpl(private val apiService: LocationServiceApiService) :
    LocationServiceApiNetworkDataSource {

    override suspend fun signIn(
        username: String,
        password: String
    ): Pair<TokenResponse?, ResponseStates> {
        return try {
            val response = apiService.signIn(username, password).await()
            Pair(response, ResponseStates.AUTHENTICATED)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
            Pair(null, ResponseStates.NO_CONNECTIVITY)
        } catch (e: NoAuthenticityException) {
            Log.e("Authentication", "401.", e)
            Pair(null, ResponseStates.UNAUTHENTICATED)
        } catch (e: Exception) {
            Log.e("Other error", "other error,${e.message}", e)
            Pair(null, ResponseStates.ERROR)
        }
    }

    override suspend fun refreshAccessToken(token: String?): Pair<TokenResponse?, ResponseStates> {
        return try {
            val response = apiService.refreshToken(token).await()
            Pair(response, ResponseStates.AUTHENTICATED)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
            Pair(null, ResponseStates.NO_CONNECTIVITY)
        } catch (e: NoAuthenticityException) {
            Log.e("Authentication", "401.", e)
            Pair(null, ResponseStates.UNAUTHENTICATED)
        } catch (e: Exception) {
            Log.e("Other error", "other error,${e.message}", e)
            Pair(null, ResponseStates.ERROR)
        }
    }

    override suspend fun searchLocations(query: String?): List<Location>? {
        return try {
            apiService.searchLocations(query).await()
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
            null
        } catch (e: NoAuthenticityException) {
            Log.e("Authentication", "401.", e)
            null
        } catch (e: Exception) {
            Log.e("Other error", "other error,${e.message}", e)
            null
        }
    }

    override suspend fun uploadImage(mediaItem: MediaItem): ResponseStates {
        return try {
            val response = apiService.uploadImages(
                mediaItem.title.toPartString(),
                mediaItem.description.toPartString(),
                mediaItem.media.toPartImage(),
                mediaItem.fileType.toPartString(),
                mediaItem.latitude.toPartDouble(),
                mediaItem.longitude.toPartDouble(),
                mediaItem.locationId.toPartString()
            ).await()
            Log.e("response", response.toString())
            ResponseStates.AUTHENTICATED
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
            ResponseStates.NO_CONNECTIVITY
        } catch (e: NoAuthenticityException) {
            Log.e("Authentication", "401.", e)
            ResponseStates.UNAUTHENTICATED
        } catch (e: Exception) {
            Log.e("Other error", "other error,${e.message}", e)
            ResponseStates.ERROR
        }
    }

    fun String.toPartImage(): MultipartBody.Part {
        var file = File(this)
        if (!file.exists()) throw FileNotFoundException()
        val requestFile: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        return MultipartBody.Part.createFormData("media", file.name, requestFile)
    }

    fun String.toPartString(): RequestBody {
        return RequestBody.create(MediaType.parse("multipart/form-data"), this)
    }

    fun Double.toPartDouble(): RequestBody {
        return RequestBody.create(MediaType.parse("multipart/form-data"), this.toString())
    }
}