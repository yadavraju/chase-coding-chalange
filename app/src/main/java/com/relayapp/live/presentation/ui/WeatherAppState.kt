package com.relayapp.live.presentation.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.relayapp.live.core.Constants.AUTH_SCREEN
import com.relayapp.live.core.Constants.PROFILE_SCREEN
import com.relayapp.live.core.Constants.REFERRAL_SCREEN

sealed class Screen(val route: String) {
    object Home : Screen(AUTH_SCREEN)
    object ReferralScreen : Screen(REFERRAL_SCREEN)
    object ProfileScreen : Screen(PROFILE_SCREEN)
    object NextScreenNavigation : Screen("sevenDays/{lat}-{long}") {
        fun createRoute(lat: Float, long: Float) = "sevenDays/$lat-$long"
    }
}

@Composable
fun rememberWeatherAppState(
    controller: NavHostController = rememberNavController()
) = remember(controller) {
    WeatherAppState(controller)
}

class WeatherAppState(val controller: NavHostController) {
    fun navigateToSevenDays(lat: Float, long: Float) {
        controller.navigate(Screen.NextScreenNavigation.createRoute(lat, long))
    }

    fun navigateBack() {
        controller.popBackStack()
    }

    fun openAppSetting(context: Context) {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    fun navigateToProfileScreen() = controller.navigate(Screen.ProfileScreen.route)

    fun navigateToReferralScreen() = controller.navigate(Screen.ReferralScreen.route)

    fun navigateToAuthScreen() = controller.navigate(Screen.Home.route)
}
