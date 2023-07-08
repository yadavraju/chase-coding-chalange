package com.relayapp.live.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.relayapp.live.R
import com.relayapp.live.presentation.ui.theme.TradeUpColors
import com.relayapp.live.presentation.ui.theme.TradeUpTypography
import com.relayapp.live.presentation.ui.theme.navigationBackIconColor

@Composable
fun RootView(
    modifier: Modifier = Modifier,
    @StringRes titleResId: Int,
    elevation: Dp = 0.dp,
    textAlign: TextAlign = TextAlign.Center,
    isSearchScreen: Boolean = false,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            if (isSearchScreen) {
                SearchToolbar(
                    searchValue = remember { mutableStateOf("") }
                )
            } else {
                CommonToolbar(titleResId, elevation, textAlign)
            }
        },
        content = { pageContent.invoke(it) }
    )
}

@Composable
fun CommonToolbar(
    @StringRes titleResId: Int,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    textAlign: TextAlign = TextAlign.Start,
) {
    TopAppBar(
        title = {
            Text(
                stringResource(titleResId),
                textAlign = textAlign,
                modifier = Modifier.fillMaxWidth(),
                style = TradeUpTypography.h2
            )
        },
        backgroundColor = TradeUpColors.primary,
        modifier = Modifier.fillMaxWidth(),
        elevation = elevation
    )
}

@Composable
fun SearchToolbar(
    searchValue: MutableState<String>,
) {
    val isValidFilled by remember { mutableStateOf(true) }
    CommonEditText(
        modifier = Modifier
            .wrapContentHeight()
            .padding(start = 8.dp, end = 8.dp),
        error = isValidFilled,
        text = searchValue.value,
        placeHolderText = "Search event..",
        focusedBorderColor = MaterialTheme.colors.primary,
        unfocusedBorderColor = MaterialTheme.colors.primary,
        onValueChange = {
            searchValue.value = it
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_search_small),
                contentDescription = "search_event",
                tint = TradeUpColors.navigationBackIconColor.copy(0.5f),
            )
        }
    )
}

@Composable
fun JRToolbarWithNavIcon(
    @StringRes titleResId: Int,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    pressOnBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                stringResource(titleResId),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                style = TradeUpTypography.h2
            )
        },
        elevation = elevation,
        navigationIcon = {
            Icon(
                rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = null,
                tint = TradeUpColors.navigationBackIconColor,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { pressOnBack.invoke() }
            )
        },
        backgroundColor = TradeUpColors.primary,
        modifier = Modifier.fillMaxWidth()
    )
}