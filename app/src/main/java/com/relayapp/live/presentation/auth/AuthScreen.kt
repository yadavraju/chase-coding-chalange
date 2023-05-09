package com.relayapp.live.presentation.auth

import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.relayapp.live.presentation.auth.components.AuthContent
import com.relayapp.live.presentation.auth.components.OneTapSignIn
import com.relayapp.live.presentation.auth.components.SignInWithGoogle

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        content = { padding ->
            AuthContent(
                padding = padding,
                oneTapSignIn = {
                    viewModel.oneTapSignIn()
                }
            )
        },
        scaffoldState = scaffoldState
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

//    SignInWithGoogle(
//        navigateToHomeScreen = { signedIn ->
//            Log.e("Raju", "Signed in sucess" + signedIn)
//            if (signedIn) {
//                Log.e("Raju", "Signed in sucess")
//            }
//        }
//    )
}