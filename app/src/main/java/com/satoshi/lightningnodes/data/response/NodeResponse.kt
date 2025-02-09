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
) {
    companion object {
        fun mock() = NodeResponse(
            alias = "WalletOfSatoshi.com",
            capacity = 3000000,
            channels = 8502,
            city = CityResponse.mock(),
            country = CountryResponse.mock(),
            firstSeen = 5977,
            publicKey = "035e4ff418fc8b5554c5d9eea66396c227bd429a3251c8cbc711002ba215bfc226",
            updatedAt = 7068L
        )
    }
}