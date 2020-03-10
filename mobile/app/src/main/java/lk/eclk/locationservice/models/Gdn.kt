package lk.eclk.locationservice.models

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import lk.eclk.locationservice.models.PolingDivision

data class Gdn(
    @SerializedName("gdn_status")
    val gdnStatus: Boolean,
    @SerializedName("gnd_code")
    val gndCode: String,
    @SerializedName("name_english")
    val nameEnglish: String,
    @SerializedName("name_sinhala")
    val nameSinhala: String,
    @SerializedName("name_tamil")
    val nameTamil: String,
    @SerializedName("polingdivision")
    @Embedded(prefix = "poling_division_")
    val polingDivision: PolingDivision
)