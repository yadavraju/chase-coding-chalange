package com.relayapp.live.presentation.ui.dashboard

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.relayapp.live.core.jetframework.SetupSystemUi
import com.relayapp.live.presentation.navigation.AppNavigationProvider
import com.relayapp.live.presentation.profile1.NavGraphs
import com.relayapp.live.presentation.ui.theme.RelayTheme
import com.relayapp.live.presentation.ui.theme.TradeUpColors

@Composable
fun MainRoot(
    finish: () -> Unit
) {
    val navController = rememberNavController()
    val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
    val destination = currentBackStackEntryAsState?.destination?.route
        ?: NavGraphs.root.startRoute.route
    if (destination == NavGraphs.root.startRoute.route) {
        BackHandler { finish() }
    }

    RelayTheme() {
        SetupSystemUi(rememberSystemUiController(), TradeUpColors.primary)
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = TradeUpColors.background
        ) {
            DestinationsNavHost(
                navController = navController,
                navGraph = NavGraphs.root,
                dependenciesContainerBuilder = {
                    dependency(AppNavigationProvider(navController))
                }
            )
        }
    }
}