package lk.eclk.locationservice.data.remote.responses

import com.google.gson.annotations.SerializedName
import lk.eclk.locationservice.models.Location

data class LocationResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    @SerializedName("results")
    val results: List<Location>
)