package lk.eclk.locationservice.data.remote.responses.locationsresponse

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

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
    @Embedded(prefix = "poling_division_")
    @SerializedName("polingdivision")
    val polingDivision: PolingDivision
)