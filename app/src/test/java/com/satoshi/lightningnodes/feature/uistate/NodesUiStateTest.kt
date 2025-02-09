package com.satoshi.lightningnodes.feature.uistate

import com.satoshi.lightningnodes.commons.navigation.ScreenState
import com.satoshi.lightningnodes.domain.model.Node
import com.satoshi.lightningnodes.feature.nodes.NodesUiState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NodesUiStateTest {

    private lateinit var uiState: NodesUiState

    @Before
    fun setup() {
        uiState = NodesUiState()
    }

    @Test
    fun `when onScreenContent then update screenState`() {
        uiState.onScreenContent(NODE_LIST_MOCK)

        assertFalse(uiState.isRefreshing.value)
        assertEquals(uiState.screenState.value, ScreenState.ScreenContent)
        assertEquals(uiState.presentation.value, EXPECTED_PRESENTATION_MOCK)
    }

    @Test
    fun `given is refreshing true, when onScreenProgress then update screenState`() {
        uiState.onScreenProgress(isRefreshing = true)

        assertTrue(uiState.isRefreshing.value)
        assertEquals(uiState.screenState.value, ScreenState.ScreenContent)
    }

    @Test
    fun `given is refreshing false, when onScreenProgress then update screenState`() {
        uiState.onScreenProgress(isRefreshing = false)

        assertFalse(uiState.isRefreshing.value)
        assertEquals(uiState.screenState.value, ScreenState.ScreenProgress)
    }

    @Test
    fun `when showError then update screenState`() {
        uiState.onScreenError(ERROR_MOCK)

        assertEquals(uiState.screenState.value, ScreenState.ScreenError(ERROR_MOCK))
    }

    companion object {
        private const val ERROR_MOCK = "Ocorreu um erro"
        private val NODE_LIST_MOCK = listOf(Node.mock())
        private val EXPECTED_PRESENTATION_MOCK = listOf(
            NodesUiState.Presentation(
                publicKey = "035e4ff418fc8b5554c5d9eea66396c227bd429a3251c8cbc711002ba215bfc226",
                alias = "WalletOfSatoshi.com",
                channels = "8502",
                capacity = "0.03000000 BTC",
                firstSeen = "31/12/1969 às 22:39",
                updatedAt = "31/12/1969 às 22:57",
                city = "Vancôver",
                country = "Canadá"
            )
        )

    }
}