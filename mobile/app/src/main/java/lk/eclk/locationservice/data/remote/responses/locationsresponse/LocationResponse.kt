package lk.eclk.locationservice.data.remote.responses.locationsresponse

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    @SerializedName("results")
    val results: List<Location>
)