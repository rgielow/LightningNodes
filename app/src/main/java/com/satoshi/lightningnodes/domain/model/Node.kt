package com.satoshi.lightningnodes.domain.model

data class Node(
    val alias: String,
    val capacity: Long,
    val channels: Int,
    val city: City?,
    val country: Country?,
    val firstSeen: Long,
    val publicKey: String,
    val updatedAt: Long
) {
    companion object {
        fun mock() = Node(
            alias = "WalletOfSatoshi.com",
            capacity = 3000000,
            channels = 8502,
            city = City.mock(),
            country = Country.mock(),
            firstSeen = 5977,
            publicKey = "035e4ff418fc8b5554c5d9eea66396c227bd429a3251c8cbc711002ba215bfc226",
            updatedAt = 7068
        )
    }
}