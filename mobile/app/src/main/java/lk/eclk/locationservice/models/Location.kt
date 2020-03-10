package lk.eclk.locationservice.models

import com.google.gson.annotations.SerializedName

data class Location(
    val code: String,
    @SerializedName("coordinate_east")
    val coordinateEast: Double,
    @SerializedName("coordinate_north")
    val coordinate_north: Double,
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