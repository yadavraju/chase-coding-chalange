package com.relayapp.live.presentation.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.relayapp.live.presentation.auth.AuthScreen
import com.relayapp.live.presentation.auth.AuthViewModel
import com.relayapp.live.presentation.auth.ReferralScreen
import com.relayapp.live.presentation.ui.dashboard.profilescreen.ProfileScreen
import com.relayapp.live.presentation.ui.day.SevenDaysScreen

@Composable
fun RelayApp(
    appState: WeatherAppState = rememberWeatherAppState(),
    navigateToDashBoard: () -> Unit = {}
) {
    NavHost(
        navController = appState.controller,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val viewModel = hiltViewModel<AuthViewModel>()
            val viewState by viewModel.state.collectAsStateWithLifecycle()

            Log.e("Raju", "viewState" + viewModel.isUserAuthenticated)
            Log.e("Raju", "viewState1" + (viewState.authResponse?.newuser == true))
            if (viewState.authResponse?.newuser == true) {
                Log.e("Raju", "navigateToReferralScreen")
                LaunchedEffect(key1 = Unit) {
                    appState.navigateToReferralScreen()
                }
            } else if (viewModel.isUserAuthenticated || viewState.authResponse?.newuser == false) {
                Log.e("Raju", "navigateToProfileScreen")
                LaunchedEffect(key1 = Unit) {
                    navigateToDashBoard()
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
            ProfileScreen(navigateToAuthScreen = {
                appState.navigateBack()
                appState.navigateToAuthScreen()
            })
        }

        composable(Screen.ReferralScreen.route) {
            ReferralScreen(navigateToAuthScreen = {
                appState.navigateToProfileScreen()
            })
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
