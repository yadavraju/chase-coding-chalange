package com.relayapp.live.presentation.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.relayapp.live.core.extesion.getActivity
import com.relayapp.live.core.extesion.launchActivity
import com.relayapp.live.core.jetframework.SetupSystemUi
import com.relayapp.live.data.local.pref.PrefsHelper
import com.relayapp.live.presentation.profile1.ProfileScreen
import com.relayapp.live.presentation.ui.MainActivity
import com.relayapp.live.presentation.ui.theme.RelayTheme
import com.relayapp.live.presentation.ui.theme.TradeUpColors
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {

    @Inject
    lateinit var prefsHelper: PrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainRoot() {}
//            RelayTheme() {
//                SetupSystemUi(rememberSystemUiController(), TradeUpColors.primary)
//                ProfileScreen(
//                    navigateToAuthScreen = {
//                        prefsHelper.clearEverything() {
//                            navigateToDashBoard()
//                        }
//                    }
//                )
//            }
        }
    }

    private fun navigateToDashBoard() {
        this.getActivity<DashboardActivity>()?.launchActivity<MainActivity> { finish() }
    }
}