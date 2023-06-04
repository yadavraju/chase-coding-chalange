package com.relayapp.live.presentation.ui.dashboard

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsPadding
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.relayapp.live.presentation.navigation.NavigationProvider
import com.relayapp.live.presentation.profile1.ProfileScreen
import com.relayapp.live.presentation.ui.theme.TradeUpColors
import com.relayapp.live.presentation.ui.theme.selectedBottomItemColor
import com.relayapp.live.presentation.ui.theme.unselectedBottomItemColor

@OptIn(ExperimentalMaterialApi::class)
@RootNavGraph(start = true)
@Destination()
@Composable
fun BottomNavigationScreen(navigator: NavigationProvider) {
    val scaffoldState = rememberScaffoldState()
    val (currentBottomTab, setCurrentBottomTab) = rememberSaveable {
        mutableStateOf(BottomBarItem.LIVE)
    }
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    Crossfade(currentBottomTab) { bottomTab ->
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = { HomeBottomNavigation(bottomTab, setCurrentBottomTab) },
            content = {
                val modifier = Modifier.padding(it)
                when (bottomTab) {
                    BottomBarItem.LIVE -> LiveScreen(
                        modifier = modifier,
                        navigator = navigator,
                    )
                    BottomBarItem.SEARCH -> HomeEventScreen(
                        modifier = modifier,
                        navigator = navigator
                    )
                    BottomBarItem.COINS -> CoinsScreen(
                        modifier = modifier,
                        navigator = navigator,
                    )
                    BottomBarItem.CHAT -> ProfileScreen()
                    BottomBarItem.PROFILE -> ProfileScreen()
                }
            }
        )
    }
}

@Composable
private fun HomeEventScreen(modifier: Modifier, navigator: NavigationProvider) {
    Text(text = "home")
}

@Composable
private fun CoinsScreen(modifier: Modifier, navigator: NavigationProvider) {
    Text(text = "home")
}

@Composable
private fun HomeBottomNavigation(
    bottomTab: BottomBarItem,
    setCurrentBottomTab: (BottomBarItem) -> Unit
) {
    val pages = BottomBarItem.values()
    BottomNavigation(
        backgroundColor = TradeUpColors.primary,
    ) {
        pages.forEach { page ->
            val selected = page == bottomTab
            val selectedLabelColor = if (selected) {
                selectedBottomItemColor
            } else {
                unselectedBottomItemColor
            }
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = rememberVectorPainter(image = ImageVector.vectorResource(page.icon)),
                        contentDescription = page.title
                    )
                },
                label = {
                    Text(
                        text = page.title,
                        color = selectedLabelColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                selected = selected,
                onClick = {
                    setCurrentBottomTab.invoke(page)
                },
                selectedContentColor = selectedBottomItemColor,
                unselectedContentColor = unselectedBottomItemColor,
                alwaysShowLabel = true,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}