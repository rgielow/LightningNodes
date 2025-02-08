package com.satoshi.lightningnodes.feature.nodes

sealed class NodesScreenAction {
    data object OnCloseClicked : NodesScreenAction()
    data object OnRefreshPulled : NodesScreenAction()
    data object OnRetryClicked : NodesScreenAction()
    data class OnNodeClicked(val publicKey: String) : NodesScreenAction()
}

fun NodesScreenAction.fold(
    onCloseClicked: () -> Unit = {},
    onRefreshPulled: () -> Unit = {},
    onRetryClicked: () -> Unit = {},
    onNodeClicked: (publicKey: String) -> Unit = {}

) {
    when (this) {
        is NodesScreenAction.OnCloseClicked -> onCloseClicked()
        is NodesScreenAction.OnRefreshPulled -> onRefreshPulled()
        is NodesScreenAction.OnNodeClicked -> onNodeClicked(publicKey)
        is NodesScreenAction.OnRetryClicked -> onRetryClicked()
    }

}