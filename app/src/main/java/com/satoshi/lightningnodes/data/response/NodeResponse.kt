package com.satoshi.lightningnodes.data.response

import com.google.gson.annotations.SerializedName

data class NodeResponse(
    @SerializedName("alias") val alias: String?,
    @SerializedName("capacity") val capacity: Long?,
    @SerializedName("channels") val channels: Int?,
    @SerializedName("city") val city: CityResponse?,
    @SerializedName("country") val country: CountryResponse?,
    @SerializedName("firstSeen") val firstSeen: Long?,
    @SerializedName("publicKey") val publicKey: String?,
    @SerializedName("updatedAt") val updatedAt: Long?
)