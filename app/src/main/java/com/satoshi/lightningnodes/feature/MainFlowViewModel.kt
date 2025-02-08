package com.satoshi.lightningnodes.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satoshi.lightningnodes.commons.events.ChannelEventSenderImpl
import com.satoshi.lightningnodes.commons.events.EventSender
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainFlowViewModel @Inject constructor() : ViewModel(),
    EventSender<MainFlowViewModel.Navigation> by ChannelEventSenderImpl() {

    fun navigate(navigation: Navigation) {
        viewModelScope.sendEvent(navigation)
    }

    sealed class Navigation(
        val route: String = EMPTY_STRING,
        val popStack: Boolean = false
    ) {
        data object Home : Navigation(route = "home-nodes")
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}