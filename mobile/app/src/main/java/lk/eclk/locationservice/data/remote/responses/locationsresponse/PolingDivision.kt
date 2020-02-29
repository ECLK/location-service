package lk.eclk.locationservice.data.remote.responses.locationsresponse

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class PolingDivision(
    @Embedded(prefix = "electoral_district_")
    @SerializedName("electoral_district")
    val electoralDistrict: ElectoralDistrict,
    val id: Int,
    @SerializedName("name_english")
    val nameEnglish: String,
    @SerializedName("name_sinhala")
    val nameSinhala: String,
    @SerializedName("name_tamil")
    val nameTamil: String,
    @SerializedName("pd_status")
    val pdStatus: Boolean
)