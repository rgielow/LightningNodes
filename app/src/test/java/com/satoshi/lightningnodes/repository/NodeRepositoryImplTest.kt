package com.satoshi.lightningnodes.repository

import com.satoshi.lightningnodes.data.api.Api
import com.satoshi.lightningnodes.data.repository.NodeRepositoryImpl
import com.satoshi.lightningnodes.data.response.NodeResponse
import com.satoshi.lightningnodes.domain.model.Node
import com.satoshi.lightningnodes.domain.repository.NodeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException

class NodeRepositoryImplTest {

    private val api: Api = mockk(relaxed = true)

    private lateinit var repository: NodeRepository

    @Before
    fun setUp() {
        repository = NodeRepositoryImpl(api = api)
    }

    @Test
    fun `given success, when getBestConnectivityNodes, then return nodes`() = runBlocking {
        coEvery { api.getBestConnectivityNodes() } returns NODES_RESPONSE_MOCK

        val result = repository.getBestConnectivityNodes()

        coVerify(exactly = 1) { api.getBestConnectivityNodes() }
        assertEquals(EXPECTED_NODES, result)
    }

    @Test(expected = SocketTimeoutException::class)
    fun `given error, when getBestConnectivityNodes, then throw exception`(): Unit = runBlocking {
        coEvery { api.getBestConnectivityNodes() } throws SocketTimeoutException()

        repository.getBestConnectivityNodes()
    }

    private companion object {
        val NODES_RESPONSE_MOCK = listOf(NodeResponse.mock())
        val EXPECTED_NODES = listOf(Node.mock())
    }
}
