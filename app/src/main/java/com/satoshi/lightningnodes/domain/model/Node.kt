package com.satoshi.lightningnodes.domain.model

data class Node(
    val alias: String,
    val capacity: Long,
    val channels: Int,
    val city: City?,
    val country: Country,
    val firstSeen: Int,
    val publicKey: String,
    val updatedAt: Int
)