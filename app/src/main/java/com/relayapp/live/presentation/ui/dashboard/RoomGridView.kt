package com.relayapp.live.presentation.ui.dashboard

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.relayapp.live.components.EmptyView
import com.relayapp.live.components.LineLoadingItem
import com.relayapp.live.data.model.liveresponse.RoomResult
import com.relayapp.live.presentation.base.ExceptionHandleView
import com.relayapp.live.presentation.ui.dashboard.viewmodel.LiveDataViewModel

@Composable
internal fun RoomGridView(
    modifier: Modifier = Modifier,
    onPokemonClicked: (name: String) -> Unit,
    isLoading: Boolean,
    loadMoreItems: () -> Unit = {},
    roomType: String,
    viewModel: LiveDataViewModel = hiltViewModel()
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()
    ExceptionHandleView(
        state = viewState,
        snackBarHostState = scaffoldState.snackbarHostState,
        positiveAction = { _, _ -> }
    ) {
        if (viewState.response?.results?.isNotEmpty() == true) {
            RoomRootView(modifier, onPokemonClicked, viewState.response?.results!!, isLoading)
        } else {
            EmptyView()
        }
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.getRoomData(roomType)
    })
}

@Composable
internal fun RoomRootView(
    modifier: Modifier = Modifier,
    onPokemonClicked: (name: String) -> Unit,
    roomList: List<RoomResult>,
    isLoading: Boolean,
    loadMoreItems: () -> Unit = {},
) {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = .1f,
        targetValue = .4f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    BoxWithConstraints {
        val columns = when (maxWidth) {
            in 0.dp..349.dp -> 1
            in 350.dp..599.dp -> 2
            in 600.dp..899.dp -> 3
            in 900.dp..1199.dp -> 4
            else -> 5
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(20.dp),
            modifier = modifier,
        ) {
            items(roomList, key = { it.id }) { room ->
                VideoItem(
                    onClick = {  },
                    roomResult = room,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (isLoading) {
                items(5) { index ->
                    LaunchedEffect(Unit) {
                        if (index == 0) loadMoreItems()
                    }
                    LineLoadingItem(alpha = alpha)
                }
            }
        }
    }
}