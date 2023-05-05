package com.example.weather.presentation.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.weather.presentation.ui.day.SevenDaysScreen
import com.example.weather.presentation.ui.home.HomeScreen
import com.example.weather.presentation.ui.newslist.NewsListScreen

@Composable
fun WeatherApp(appState: WeatherAppState = rememberWeatherAppState()) {
    NavHost(
        navController = appState.controller,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
//            NewsListScreen()
            HomeScreen(
                appState = appState,
                viewModel = hiltViewModel(),
                hamburgerNavigationClicked = {
                    appState.navigateToSevenDays(0.0f, 0.0f)
                },
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
