package com.relayapp.live.presentation.profile1.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.relayapp.live.components.LoadingView
import com.relayapp.live.domain.model.Response
import com.relayapp.live.presentation.profile1.ProfileViewModel

@Composable
fun SignOut(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: (signedOut: Boolean) -> Unit,
) {
    when (val signOutResponse = viewModel.signOutResponse) {
        is Response.Loading -> LoadingView()
        is Response.Success -> signOutResponse.data?.let { signedOut ->
            LaunchedEffect(signedOut) {
                navigateToAuthScreen(signedOut)
            }
        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(signOutResponse.e)
        }
    }
}