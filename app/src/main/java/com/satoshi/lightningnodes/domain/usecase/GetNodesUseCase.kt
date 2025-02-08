package com.satoshi.lightningnodes.domain.usecase

import com.satoshi.lightningnodes.commons.network.safeRunDispatcher
import com.satoshi.lightningnodes.data.repository.NodeRepositoryImpl
import javax.inject.Inject

class GetNodesUseCase @Inject constructor(
    private val repository: NodeRepositoryImpl
) {
    suspend fun execute() = safeRunDispatcher {
        repository.getBestConnectivityNodes()
    }
}