package com.satoshi.lightningnodes.feature.nodes

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.satoshi.lightningnodes.R
import com.satoshi.lightningnodes.domain.model.Node
import com.satoshi.lightningnodes.commons.navigation.ScreenState
import com.satoshi.lightningnodes.feature.MainActivity
import com.satoshi.lightningnodes.feature.error.ErrorScreen
import com.satoshi.lightningnodes.feature.loading.LoadingScreen
import com.satoshi.lightningnodes.feature.nodes.NodesViewModel.ScreenEvent
import com.satoshi.lightningnodes.feature.nodes.NodesUiState.Presentation
import com.satoshi.lightningnodes.ui.theme.LightningNodesTheme

@SuppressLint("ContextCastToActivity")
@Composable
fun NodesScreen(
    viewModel: NodesViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) { viewModel.setup() }
    val activity = LocalContext.current as MainActivity
    Screen(uiState = viewModel.uiState, onAction = viewModel::onActionEvent)
    EventConsumer(activity = activity, viewModel = viewModel)

}

@Composable
private fun EventConsumer(
    activity: MainActivity,
    viewModel: NodesViewModel
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.eventsFlow.collect { event ->
            when (event) {
                ScreenEvent.Finish -> activity.finish()
            }
        }
    }
}

@Composable
private fun Screen(
    uiState: NodesUiState,
    onAction: (NodesScreenAction) -> Unit
) {
    LightningNodesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
        ) {
            when (val screenState = uiState.screenState.collectAsStateWithLifecycle().value) {
                is ScreenState.ScreenProgress -> LoadingScreen()
                is ScreenState.ScreenContent -> ScreenContent(onAction = onAction, uiState = uiState)
                is ScreenState.ScreenError -> ErrorScreen(
                    onRetry = { onAction(NodesScreenAction.OnRetryClicked) },
                    error = screenState.error
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    onAction: (NodesScreenAction) -> Unit,
    uiState: NodesUiState
) {
    val presentation by uiState.presentation.collectAsStateWithLifecycle()
    val isRefreshing by uiState.isRefreshing.collectAsStateWithLifecycle()
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onAction(NodesScreenAction.OnRefreshPulled) }
    ) {
        LazyColumn(
            modifier = Modifier.background(Color.LightGray)
        ) {
            items(presentation.size) {
                Node(presentation = presentation[it])
                HorizontalDivider(color = Color.White)
            }
        }
    }
}

@Composable
private fun Node(presentation: Presentation) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = presentation.alias, color = Color.DarkGray, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.padding(4.dp))
        LabelWithValue(label = stringResource(R.string.node_channels), value = presentation.channels)
        LabelWithValue(label = stringResource(R.string.node_capacity), value = presentation.capacity)
        LabelWithValue(label = stringResource(R.string.node_first_seen), value = presentation.firstSeen)
        LabelWithValue(label = stringResource(R.string.node_updated_at), value = presentation.updatedAt)
        LabelWithValue(label = stringResource(R.string.node_city), value = presentation.city)
        LabelWithValue(label = stringResource(R.string.node_country), value = presentation.country)
        LabelWithValue(label = stringResource(R.string.node_public_key), value = presentation.publicKey)
    }
}

@Composable
private fun LabelWithValue(
    label: String,
    value: String
) {
    Row {
        Text(
            text = label,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            fontFamily = FontFamily.Default,
            text = value
        )
    }
}

@Composable
@Preview
private fun Preview() {
    Screen(uiState = NodesUiState().apply {
        onScreenContent(listOf(Node.mock()))
    }) { }
}