package lk.eclk.locationservice.data.remote.responses.locationsresponse

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey(autoGenerate = false)
    val code: String,
    @SerializedName("coordinate_east")
    val coordinateEast: Double,
    @SerializedName("coordinate_north")
    val coordinateNorth: Double,
    @Embedded(prefix = "gdn_")
    val gdn: Gdn,
    val latitude: Double,
    @SerializedName("location_status")
    val locationStatus: Boolean,
    @SerializedName("longitute")
    val longitude: Double,
    @SerializedName("name_english")
    val nameEnglish: String,
    @SerializedName("name_sinhala")
    val nameSinhala: String,
    @SerializedName("name_tamil")
    val nameTamil: String
)