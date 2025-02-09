package com.satoshi.lightningnodes.feature.nodes

sealed class NodesScreenAction {
    data object OnCloseClicked : NodesScreenAction()
    data object OnRefreshPulled : NodesScreenAction()
    data object OnRetryClicked : NodesScreenAction()
}

fun NodesScreenAction.fold(
    onCloseClicked: () -> Unit = {},
    onRefreshPulled: () -> Unit = {},
    onRetryClicked: () -> Unit = {},
) {
    when (this) {
        is NodesScreenAction.OnCloseClicked -> onCloseClicked()
        is NodesScreenAction.OnRefreshPulled -> onRefreshPulled()
        is NodesScreenAction.OnRetryClicked -> onRetryClicked()
    }

}