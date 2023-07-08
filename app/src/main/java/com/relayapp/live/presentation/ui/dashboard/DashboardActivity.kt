package com.relayapp.live.presentation.ui.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.relayapp.live.core.extesion.getActivity
import com.relayapp.live.core.extesion.launchActivity
import com.relayapp.live.data.local.pref.PrefsHelper
import com.relayapp.live.presentation.ui.MainActivity
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