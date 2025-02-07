package com.satoshi.lightningnodes.domain.repository

import com.satoshi.lightningnodes.domain.model.Node

interface NodeRepository {
    suspend fun getBestConnectivityNodes(): List<Node>
}