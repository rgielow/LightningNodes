package com.satoshi.lightningnodes.feature.nodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satoshi.lightningnodes.commons.events.ChannelEventSenderImpl
import com.satoshi.lightningnodes.commons.events.EventSender
import com.satoshi.lightningnodes.commons.network.fold
import com.satoshi.lightningnodes.domain.usecase.GetNodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NodesViewModel @Inject constructor(
    private val getNodesUseCase: GetNodesUseCase,
    val uiState: NodesUiState
) : ViewModel(),
    EventSender<NodesViewModel.ScreenEvent> by ChannelEventSenderImpl() {

    fun setup() {
        getNodes()
    }

    fun onActionEvent(action: NodesScreenAction) {
        action.fold(
            onCloseClicked = ::finish,
            onRefreshPulled = { getNodes(isRefreshing = true) },
            onRetryClicked = ::getNodes
        )
    }

    private fun getNodes(isRefreshing: Boolean = false) = viewModelScope.launch {
        uiState.onScreenProgress(isRefreshing = isRefreshing)
        getNodesUseCase.execute().fold(
            onFailure = { uiState.onScreenError(it) },
            onSuccess = { uiState.onScreenContent(it) }
        )
    }

    private fun finish() = viewModelScope.sendEvent(ScreenEvent.Finish)

    sealed class ScreenEvent {
        data object Finish : ScreenEvent()
    }
}
