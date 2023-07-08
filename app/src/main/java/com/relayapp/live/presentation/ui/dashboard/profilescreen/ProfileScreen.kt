package com.relayapp.live.presentation.ui.dashboard.profilescreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.relayapp.live.R
import com.relayapp.live.components.RootView
import com.relayapp.live.core.extesion.getActivity
import com.relayapp.live.core.extesion.launchActivity
import com.relayapp.live.presentation.profile1.components.ProfileContent
import com.relayapp.live.presentation.ui.MainActivity
import com.relayapp.live.presentation.ui.dashboard.DashboardActivity
import com.relayapp.live.presentation.ui.dashboard.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit = {}
) {
    val context = LocalContext.current
    RootView(
        modifier = modifier,
        titleResId = R.string.toolbar_profile_title
    ) { padding ->
        ProfileContent(
            modifier = Modifier.fillMaxSize(),
            padding = padding,
            photoUrl = viewModel.photoUrl,
            displayName = viewModel.displayName,
            email = viewModel.email,
            onLogout = {
                viewModel.signOut()
                context.getActivity<DashboardActivity>()
                    ?.launchActivity<MainActivity> { context.getActivity<MainActivity>()?.finish() }
            }
        )
    }
}