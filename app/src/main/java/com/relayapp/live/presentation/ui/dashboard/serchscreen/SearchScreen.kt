package com.relayapp.live.presentation.ui.dashboard.serchscreen

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.relayapp.live.R
import com.relayapp.live.components.RootView
import com.relayapp.live.presentation.base.ExceptionHandleView
import com.relayapp.live.presentation.navigation.NavigationProvider
import com.relayapp.live.presentation.ui.dashboard.RoomGridView
import com.relayapp.live.presentation.ui.dashboard.viewmodel.CoinDataViewModel

@Composable
fun SearchScreen(
    modifier: Modifier,
    navigator: NavigationProvider,
    viewModel: CoinDataViewModel = hiltViewModel()
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()
    RootView(
        modifier = modifier,
        titleResId = R.string.toolbar_search_title,
        isSearchScreen = true
    ) { _ ->
        ExceptionHandleView(
            state = viewState,
            snackBarHostState = scaffoldState.snackbarHostState,
            positiveAction = { _, _ -> }
        ) {
            RoomGridView(
                modifier = modifier,
                onPokemonClicked = { _ -> },
                isLoading = false,
                roomType = "getAllPublicRooms"
            )
        }
    }
}
