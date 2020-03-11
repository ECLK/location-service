package lk.eclk.locationservice.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "media_items")
class MediaItem(
    val id: String,
    val title: String,
    val description: String,
    val media: String,
    @SerializedName("file_type")
    val fileType: String,
    val latitude: Double,
    val longitude: Double,
    val status: Int,
    @SerializedName("created_time")
    val createdTime: Long,
    @SerializedName("updated_time")
    val updatedTime: Long,
    @SerializedName("location_id")
    val locationId: String,
    @Expose
    val state: Int
){
    @PrimaryKey(autoGenerate = true)
    private var pk:Int = 0;
}