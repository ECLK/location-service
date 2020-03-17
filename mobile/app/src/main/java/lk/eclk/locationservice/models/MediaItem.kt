package lk.eclk.locationservice.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileNotFoundException


@Entity(tableName = "media_items")
class MediaItem(
    val id: Int,
    val title: String,
    val description: String,
    val media: String,
    @SerializedName("file_type")
    val fileType: String,
    val latitude: Double,
    val longitude: Double,
    val status: Int,
    @SerializedName("created_time")
    val createdTime: String,
    @SerializedName("updated_time")
    val updatedTime: String,
    @SerializedName("location")
    val locationId: String,
    @Expose
    val state: Int
) {
    @PrimaryKey(autoGenerate = true)
    private var pk: Int = 0;

    fun String.toPartImage(): MultipartBody.Part {
        var file = File(media)
        if (!file.exists()) throw FileNotFoundException()
        val requestFile: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        return MultipartBody.Part.createFormData("media", file.name, requestFile)
    }

    fun String.toPartString(): RequestBody {
        return RequestBody.create(MediaType.parse("multipart/form-data"), this)
    }

    fun Double.toPartDouble():RequestBody{
        return RequestBody.create(MediaType.parse("multipart/form-data"), this.toString())
    }
}