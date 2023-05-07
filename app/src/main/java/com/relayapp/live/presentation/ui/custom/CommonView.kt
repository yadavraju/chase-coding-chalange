package com.relayapp.live.presentation.ui.custom

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.relayapp.live.R

@Composable
fun CommonToolbarScreen(
    hamburgerNavigationClicked: (() -> Unit)? = null,
    openSearchView: (() -> Unit)? = null,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    backgroundColor: Color = if (isSystemInDarkTheme()) Color.Transparent else Color.White,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp, top = 12.dp),
                style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = { hamburgerNavigationClicked?.invoke() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_menu_drawer),
                    contentDescription = stringResource(R.string.menu),
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        actions = {
            IconButton(onClick = { openSearchView?.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search_city),
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        backgroundColor = backgroundColor,
        elevation = elevation
    )
}