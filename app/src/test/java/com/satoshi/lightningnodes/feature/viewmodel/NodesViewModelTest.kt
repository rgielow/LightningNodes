package com.satoshi.lightningnodes.feature.viewmodel

import com.satoshi.lightningnodes.CoroutinesTestRule
import com.satoshi.lightningnodes.domain.model.Node
import com.satoshi.lightningnodes.domain.usecase.GetNodesUseCase
import com.satoshi.lightningnodes.feature.nodes.NodesUiState
import com.satoshi.lightningnodes.feature.nodes.NodesViewModel
import com.satoshi.lightningnodes.feature.nodes.NodesViewModel.ScreenEvent
import com.satoshi.lightningnodes.commons.network.Result
import com.satoshi.lightningnodes.feature.nodes.NodesScreenAction
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class NodesViewModelTest {

    @get:Rule
    val testRule = CoroutinesTestRule()

    private val getNodesUseCase: GetNodesUseCase = mockk(relaxed = true)
    private val uiState: NodesUiState = mockk(relaxed = true)
    private val screenEvent: (ScreenEvent) -> (Unit) = mockk(relaxed = true)

    private lateinit var viewModel: NodesViewModel

    @Before
    fun setup() {
        viewModel = NodesViewModel(
            getNodesUseCase = getNodesUseCase,
            uiState = uiState
        )
        prepareObservers()
    }

    @Test
    fun `given success, when setup, then show content`() {
        coEvery { getNodesUseCase.execute() } returns Result.Success(NODE_LIST_MOCK)

        viewModel.setup()

        coVerifyOrder {
            uiState.onScreenProgress(false)
            getNodesUseCase.execute()
            uiState.onScreenContent(NODE_LIST_MOCK)
        }
    }

    @Test
    fun `given error, when setup, then show error`() {
        coEvery { getNodesUseCase.execute() } returns Result.Failure(ERROR_MOCK)

        viewModel.setup()

        coVerifyOrder {
            uiState.onScreenProgress(false)
            getNodesUseCase.execute()
            uiState.onScreenError(ERROR_MOCK)
        }
    }

    @Test
    fun `when onActionEvent is called with OnCloseClicked, then send Finish Event`() {
        viewModel.onActionEvent(NodesScreenAction.OnCloseClicked)

        verify {
            screenEvent(ScreenEvent.Finish)
        }
    }

    @Test
    fun `when onActionEvent is called with OnRetryClicked, then calls getNodes`() {
        coEvery { getNodesUseCase.execute() } returns Result.Success(NODE_LIST_MOCK)

        viewModel.onActionEvent(NodesScreenAction.OnRetryClicked)

        coVerifyOrder {
            uiState.onScreenProgress(false)
            getNodesUseCase.execute()
            uiState.onScreenContent(NODE_LIST_MOCK)
        }
    }

    @Test
    fun `when onActionEvent is called with OnRefreshPulled, then calls getNodes`() {
        coEvery { getNodesUseCase.execute() } returns Result.Success(NODE_LIST_MOCK)

        viewModel.onActionEvent(NodesScreenAction.OnRefreshPulled)

        coVerifyOrder {
            uiState.onScreenProgress(true)
            getNodesUseCase.execute()
            uiState.onScreenContent(NODE_LIST_MOCK)
        }
    }

    private fun prepareObservers() {
        testRule.testCoroutineScope.launch {
            viewModel.eventsFlow.collect(screenEvent)
        }
    }

    companion object {
        private val NODE_LIST_MOCK = listOf(Node.mock())
        private const val ERROR_MOCK = "Ocorreu um erro"
    }
}
