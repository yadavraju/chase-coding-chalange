package com.relayapp.live.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.identity.Identity
import com.relayapp.live.core.extesion.getActivity
import com.relayapp.live.core.extesion.launchActivity
import com.relayapp.live.core.jetframework.SetupSystemUi
import com.relayapp.live.data.local.pref.AppPrefs.Companion.USER_ID
import com.relayapp.live.data.local.pref.PrefsHelper
import com.relayapp.live.presentation.googlesignin.GoogleAuthUiClient
import com.relayapp.live.presentation.googlesignin.SignInScreen
import com.relayapp.live.presentation.googlesignin.SignInViewModel
import com.relayapp.live.presentation.profile.ProfileScreen
import com.relayapp.live.presentation.ui.dashboard.DashboardActivity
import com.relayapp.live.presentation.ui.theme.RelayTheme
import com.relayapp.live.presentation.ui.theme.TradeUpColors
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @Inject
    lateinit var prefsHelper: PrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (prefsHelper.read(USER_ID, "").isNotEmpty()) {
            navigateToDashBoard()
        } else {
            setContent {
                RelayTheme() {
                    SetupSystemUi(rememberSystemUiController(), TradeUpColors.primary)
                    RelayApp() {
                        navigateToDashBoard()
                    }
                }
            }
        }
    }

    private fun navigateToDashBoard() {
        this.getActivity<MainActivity>()?.launchActivity<DashboardActivity> { finish() }
    }

    @Composable
    fun SignInPage() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "sign_in") {
                composable("sign_in") {
                    val viewModel = viewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    LaunchedEffect(key1 = Unit) {
                        if (googleAuthUiClient.getSignedInUser() != null) {
                            navController.navigate("profile")
                        }
                    }

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if (result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult =
                                        googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                    viewModel.onSignInResult(signInResult)
                                }
                            } else {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                }
                            }
                        }
                    )

                    LaunchedEffect(key1 = state.isSignInSuccessful) {
                        if (state.isSignInSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Sign in successful",
                                Toast.LENGTH_LONG
                            ).show()

                            navController.navigate("profile")
                            viewModel.resetState()
                        }
                    }

                    SignInScreen(
                        state = state,
                        onSignInClick = {
                            lifecycleScope.launch {
                                val signInIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        }
                    )
                }
                composable("profile") {
                    ProfileScreen(
                        userData = googleAuthUiClient.getSignedInUser(),
                        onSignOut = {
                            lifecycleScope.launch {
                                googleAuthUiClient.signOut()
                                Toast.makeText(
                                    applicationContext,
                                    "Signed out",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.popBackStack()
                            }
                        }
                    )
                }
            }
        }
    }
}

