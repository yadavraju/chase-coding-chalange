package com.relayapp.live.presentation.auth

import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider.getCredential
import com.relayapp.live.presentation.auth.components.AuthContent
import com.relayapp.live.presentation.auth.components.AuthTopBar
import com.relayapp.live.presentation.auth.components.OneTapSignIn
import com.relayapp.live.presentation.auth.components.SignInWithGoogle
import com.relayapp.live.presentation.ui.theme.TradeUpColors
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier.background(TradeUpColors.primary),
        content = { padding ->
            AuthContent(
                padding = padding,
                oneTapSignIn = {
                    viewModel.oneTapSignIn()
                }
            )
        }
    )

    val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                viewModel.signInWithGoogle(result.data ?: return@rememberLauncherForActivityResult)
            } catch (it: ApiException) {
                print(it)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )

    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                navigateToProfileScreen()
            }
        }
    )
}