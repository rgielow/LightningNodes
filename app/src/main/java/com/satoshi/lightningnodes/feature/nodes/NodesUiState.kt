package com.satoshi.lightningnodes.feature.nodes

import com.satoshi.lightningnodes.commons.extensions.toBitcoin
import com.satoshi.lightningnodes.commons.extensions.toFormattedDate
import com.satoshi.lightningnodes.commons.navigation.ScreenState
import com.satoshi.lightningnodes.domain.model.City
import com.satoshi.lightningnodes.domain.model.Country
import com.satoshi.lightningnodes.domain.model.Node
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NodesUiState @Inject constructor() {

    val screenState = MutableStateFlow<ScreenState>(ScreenState.ScreenContent)
    val presentation = MutableStateFlow<List<Presentation>>((emptyList()))
    val isRefreshing = MutableStateFlow(false)

    fun onScreenContent(nodes: List<Node>) {
        setRefreshing(false)
        presentation.value = nodes.toPresentation()
        screenState.value = ScreenState.ScreenContent
    }

    fun onScreenProgress(isRefreshing: Boolean) {
        setRefreshing(isRefreshing)
        if (isRefreshing.not()) screenState.value = ScreenState.ScreenProgress
    }

    fun onScreenError(error: String) {
        screenState.value = ScreenState.ScreenError(error)
    }

    private fun setRefreshing(isRefreshing: Boolean) {
        this.isRefreshing.value = isRefreshing
    }

    private fun List<Node>.toPresentation() = map {
        Presentation(
            publicKey = it.publicKey,
            alias = it.alias,
            channels = it.channels.toString(),
            capacity = it.capacity.toBitcoin(),
            firstSeen = it.firstSeen.toFormattedDate(),
            updatedAt = it.updatedAt.toFormattedDate(),
            city = it.city.getCity(),
            country = it.country.getCountry()
        )
    }

    private fun City?.getCity() = this?.ptBR?.ifEmpty { this.en } ?: EMPTY_STRING
    private fun Country?.getCountry() = this?.ptBR?.ifEmpty { this.en } ?: EMPTY_STRING


    data class Presentation(
        val publicKey: String = EMPTY_STRING,
        val alias: String = EMPTY_STRING,
        val channels: String = EMPTY_STRING,
        val capacity: String = EMPTY_STRING,
        val firstSeen: String = EMPTY_STRING,
        val updatedAt: String = EMPTY_STRING,
        val city: String = EMPTY_STRING,
        val country: String = EMPTY_STRING
    )

    companion object {
        private const val EMPTY_STRING = ""
    }
}