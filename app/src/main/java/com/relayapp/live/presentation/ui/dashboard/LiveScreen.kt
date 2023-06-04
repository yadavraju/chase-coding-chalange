@file:OptIn(ExperimentalMaterialApi::class)

package com.relayapp.live.presentation.ui.dashboard

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import com.relayapp.live.R
import com.relayapp.live.components.EmptyView
import com.relayapp.live.components.MainScreenTopBar
import com.relayapp.live.presentation.model.list
import com.relayapp.live.presentation.navigation.NavigationProvider
import com.relayapp.live.presentation.ui.theme.TradeUpColors
import com.relayapp.live.presentation.ui.theme.TradeUpTypography

@Composable
fun LiveScreen(
    modifier: Modifier,
    navigator: NavigationProvider,
    bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
) {
    EventListBody(
        modifier = modifier,
        bottomSheetState = bottomSheetState,
        sheetContent = { EmptyView() },
        navigator = navigator,
    ) { padding ->
        LiveTabScreen(padding = padding, navigator = navigator, modifier = modifier)
    }
}

@Composable
private fun LiveTabScreen(
    padding: PaddingValues,
    navigator: NavigationProvider,
    modifier: Modifier,
) {
    Column {
        val tabsName = remember { EpisodeTabs.values().map { it.value } }
        val selectedIndex =
            rememberSaveable { mutableStateOf(EpisodeTabs.PUBLIC_TAB.ordinal) }
        ScrollableTabRow(
            selectedTabIndex = selectedIndex.value,
            backgroundColor = TradeUpColors.primary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = Color.Green,
                    height = TabRowDefaults.IndicatorHeight,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedIndex.value])
                )
            }
        ) {
            tabsName.forEachIndexed { index, stringResourceId ->
                Tab(
                    selected = index == selectedIndex.value,
                    onClick = {
                        when (stringResourceId) {
                            EpisodeTabs.PUBLIC_TAB.value -> {
                                selectedIndex.value = EpisodeTabs.PUBLIC_TAB.ordinal
                            }
                            EpisodeTabs.FAVORITE_TAB.value -> {
                                selectedIndex.value = EpisodeTabs.FAVORITE_TAB.ordinal
                            }
                            EpisodeTabs.PREMIUM_TAB.value -> {
                                selectedIndex.value = EpisodeTabs.PREMIUM_TAB.ordinal
                            }
                        }
                    },
                    text = {
                        Text(
                            text = stringResource(id = stringResourceId),
                            style = TradeUpTypography.h3,
                        )
                    }
                )
            }
        }
        when (selectedIndex.value) {
            EpisodeTabs.PUBLIC_TAB.ordinal -> {
                PokemonGrid(
                    onPokemonClicked = { _ -> },
                    pokemonList = list,
                    isLoading = false,
                )
            }
            EpisodeTabs.FAVORITE_TAB.ordinal -> {
                EmptyView()
            }
            EpisodeTabs.PREMIUM_TAB.ordinal -> {
                EmptyView()
            }
        }
    }
}

private enum class EpisodeTabs(@StringRes val value: Int) {
    PUBLIC_TAB(R.string.public_tab),
    FAVORITE_TAB(R.string.favorite_tab),
    PREMIUM_TAB(R.string.premium_tab)
}


@Composable
private fun EventListBody(
    modifier: Modifier = Modifier,
    bottomSheetState: ModalBottomSheetState,
    sheetContent: @Composable() (ColumnScope.() -> Unit),
    navigator: NavigationProvider? = null,
    pageContent: @Composable (PaddingValues) -> Unit
) {
    ModalBottomSheetLayout(
        sheetContent = sheetContent,
        modifier = modifier
            .fillMaxSize(),
        sheetState = bottomSheetState,
        sheetContentColor = TradeUpColors.background,
        sheetShape = RectangleShape,
        content = {
            Scaffold(
                topBar = { MainScreenTopBar(navigator = navigator) },
                content = { pageContent.invoke(it) }
            )
        }
    )
}