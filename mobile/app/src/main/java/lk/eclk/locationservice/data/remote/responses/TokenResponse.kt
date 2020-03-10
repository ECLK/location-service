package lk.eclk.locationservice.data.remote.responses

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access")
    val accessToken: String,
    @SerializedName("refresh")
    val refreshToken: String?
)