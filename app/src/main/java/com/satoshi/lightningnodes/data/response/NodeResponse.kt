package com.satoshi.lightningnodes.data.response

data class NodeResponse(
    val alias: String?,
    val capacity: Long?,
    val channels: Int?,
    val cityResponse: CityResponse?,
    val countryResponse: CountryResponse?,
    val firstSeen: Int?,
    val publicKey: String?,
    val updatedAt: Int?
)