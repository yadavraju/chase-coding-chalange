package com.relayapp.live.presentation.auth.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.relayapp.live.components.LoadingView
import com.relayapp.live.domain.model.Response
import com.relayapp.live.presentation.auth.AuthViewModel

@Composable
fun SignInWithGoogle(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit
) {
//    when (val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
//        is Response.Loading -> LoadingView()
//        is Response.Success -> signInWithGoogleResponse.data?.let { signedIn ->
//            LaunchedEffect(signedIn) {
//                navigateToHomeScreen(signedIn)
//            }
//        }
//        is Response.Failure -> LaunchedEffect(Unit) {
//            print(signInWithGoogleResponse.e)
//        }
//    }
}