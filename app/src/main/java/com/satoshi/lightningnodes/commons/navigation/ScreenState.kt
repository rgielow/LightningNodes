package com.satoshi.lightningnodes.commons.navigation

sealed class ScreenState {
    data object ScreenContent : ScreenState()
    data object ScreenProgress : ScreenState()
    data class ScreenError(val error: String) : ScreenState()

    fun isProgress() = this is ScreenProgress
    fun isContent() = this is ScreenContent
    fun isError() = this is ScreenError
}