package lk.eclk.locationservice.data.remote.responses.locationsresponse

import com.google.gson.annotations.SerializedName

data class ElectoralDistrict(
    @SerializedName("ed_status")
    val edStatus: Boolean,
    val id: Int,
    @SerializedName("name_english")
    val nameEnglish: String,
    @SerializedName("name_sinhala")
    val nameSinhala: String,
    @SerializedName("name_tamil")
    val nameTamil: String
)