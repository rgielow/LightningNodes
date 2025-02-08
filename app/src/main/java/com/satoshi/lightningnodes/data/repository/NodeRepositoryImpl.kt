package com.satoshi.lightningnodes.data.repository

import com.satoshi.lightningnodes.data.api.Api
import com.satoshi.lightningnodes.data.mapper.toNodes
import com.satoshi.lightningnodes.domain.repository.NodeRepository

class NodeRepositoryImpl(
    private val api: Api
) : NodeRepository {
    override suspend fun getBestConnectivityNodes() =
        api.getBestConnectivityNodes().toNodes()
}