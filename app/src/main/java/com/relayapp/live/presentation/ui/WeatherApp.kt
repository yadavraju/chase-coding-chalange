package com.relayapp.live.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.relayapp.live.presentation.auth.AuthScreen
import com.relayapp.live.presentation.auth.AuthViewModel
import com.relayapp.live.presentation.profile1.ProfileScreen
import com.relayapp.live.presentation.ui.day.SevenDaysScreen

@Composable
fun WeatherApp(appState: WeatherAppState = rememberWeatherAppState()) {

    NavHost(
        navController = appState.controller,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val viewModel = hiltViewModel<AuthViewModel>()
            if (viewModel.isUserAuthenticated) {
                LaunchedEffect(key1 = Unit) {
                    appState.navigateToProfileScreen()
                }
            } else {
                AuthScreen(
                    navigateToProfileScreen = {
                        appState.navigateToProfileScreen()
                    }
                )
            }
        }

        composable(Screen.ProfileScreen.route) {
            ProfileScreen(
                navigateToAuthScreen = {
                    appState.navigateBack()
                    appState.navigateToAuthScreen()
                }
            )
        }

        // This just added for demonstration of compose navigate nothing to do with this code challenge
        composable(Screen.NextScreenNavigation.route,
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("long") { type = NavType.FloatType }
            )
        ) { entry ->
            SevenDaysScreen(
                onBackPressed = appState::navigateBack,
                lat = entry.arguments?.getFloat("lat")?.toDouble() ?: 0.0,
                long = entry.arguments?.getFloat("long")?.toDouble() ?: 0.0
            )
        }
    }
}
