package com.satoshi.lightningnodes.feature.nodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satoshi.lightningnodes.commons.events.ChannelEventSenderImpl
import com.satoshi.lightningnodes.commons.events.EventSender
import com.satoshi.lightningnodes.domain.model.Node
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NodesViewModel @Inject constructor(
    val uiState: NodesUiState
) : ViewModel(),
    EventSender<NodesViewModel.ScreenEvent> by ChannelEventSenderImpl() {

    private var isFirstGet = true
    fun setup() {
        getNodes()
    }

    fun onActionEvent(action: NodesScreenAction) {
        action.fold(
            onCloseClicked = ::finish,
            onNodeClicked = ::navigateUp,
            onRefreshPulled = ::getNodes
        )
    }

    private fun getNodes() = viewModelScope.launch {
        uiState.onScreenProgress(isRefreshing = isFirstGet.not())
        delay(2000)
        uiState.onScreenContent(
            arrayListOf(Node.mock(), Node.mock())
        )
        isFirstGet = false
    }

    private fun finish() = viewModelScope.sendEvent(ScreenEvent.Finish)

    private fun navigateUp(publicKey: String) =
        viewModelScope.sendEvent(ScreenEvent.NavigateUp(publicKey))

    sealed class ScreenEvent {
        data object Finish : ScreenEvent()
        data class NavigateUp(val publicKey: String) : ScreenEvent()
    }
}
