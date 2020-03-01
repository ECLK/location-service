package lk.eclk.locationservice.models

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import lk.eclk.locationservice.models.ElectoralDistrict

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