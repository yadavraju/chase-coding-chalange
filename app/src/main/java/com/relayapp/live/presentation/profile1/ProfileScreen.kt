package com.relayapp.live.presentation.profile1

import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult.ActionPerformed
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.relayapp.live.core.Constants.REVOKE_ACCESS_MESSAGE
import com.relayapp.live.core.Constants.SIGN_OUT
import com.relayapp.live.core.extesion.getActivity
import com.relayapp.live.core.extesion.launchActivity
import kotlinx.coroutines.launch
import com.relayapp.live.presentation.profile1.components.ProfileContent
import com.relayapp.live.presentation.profile1.components.ProfileTopBar
import com.relayapp.live.presentation.profile1.components.RevokeAccess
import com.relayapp.live.presentation.profile1.components.SignOut
import com.relayapp.live.presentation.ui.MainActivity
import com.relayapp.live.presentation.ui.dashboard.DashboardActivity

@RootNavGraph(start = true)
@Destination
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit = {}
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            ProfileTopBar(
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            ProfileContent(
                padding = padding,
                photoUrl = viewModel.photoUrl,
                displayName = viewModel.displayName
            )
        },
        scaffoldState = scaffoldState
    )

    fun showSnackBar() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT
        )
        if (result == ActionPerformed) {
            viewModel.signOut()
        }
    }

    SignOut(
        navigateToAuthScreen = { signedOut ->
            if (signedOut) {
                context.getActivity<DashboardActivity>()
                    ?.launchActivity<MainActivity> { context.getActivity<MainActivity>()?.finish() }
            }
        },
    )

    RevokeAccess(
        navigateToAuthScreen = { accessRevoked ->
            if (accessRevoked) {
                context.getActivity<DashboardActivity>()
                    ?.launchActivity<MainActivity> { context.getActivity<MainActivity>()?.finish() }
            }
        },
        showSnackBar = {
            showSnackBar()
        }
    )
}