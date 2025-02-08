package com.satoshi.lightningnodes.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.satoshi.lightningnodes.commons.navigation.composeNavigate
import com.satoshi.lightningnodes.commons.navigation.setNavigationContent
import com.satoshi.lightningnodes.feature.MainFlowViewModel.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val flowViewModel: MainFlowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigationContent(
            startDestination = Navigation.Home.route,
            navGraphBuilder = this::navGraphBuilder,
            navEventFlow = flowViewModel.eventsFlow,
            navEvent = this::navEvent
        )
    }

    private fun navGraphBuilder(builder: NavGraphBuilder) = builder.apply {
        composable(Navigation.Home.route) {

        }
    }

    private fun navEvent(navController: NavHostController, navScreen: Navigation) {
        when (navScreen) {
            is Navigation.Home -> navController.composeNavigate(
                route = navScreen.route,
                popStack = navScreen.popStack
            )
        }
    }
}