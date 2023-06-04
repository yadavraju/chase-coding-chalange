package com.relayapp.live.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.relayapp.live.R
import com.relayapp.live.presentation.navigation.NavigationProvider
import com.relayapp.live.presentation.ui.theme.Green
import com.relayapp.live.presentation.ui.theme.TradeUpColors
import com.relayapp.live.presentation.ui.theme.TradeUpTypography
import com.relayapp.live.presentation.ui.theme.White
import com.relayapp.live.presentation.ui.theme.navigationBackIconColor

@Composable
fun MainScreenTopBar(
    modifier: Modifier = Modifier,
    isHomeScreen: Boolean = true,
    unreadNotificationCount: Int = 0,
    navigator: NavigationProvider? = null,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
    ) {
        val iconSize = 24.dp
        Box(modifier = Modifier.fillMaxSize()) {
            Crossfade(
                targetState = isHomeScreen,
                modifier = Modifier.align(Alignment.CenterStart)
            ) { isHomeScreen ->
                if (isHomeScreen) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        TTIconScaled(
//                            icon = R.drawable.ic_user_square,
//                            iconScale = IconScale.XS,
//                            padding = 0.dp
//                        )
//                        AsyncImage(
//                            model = ImageRequest.Builder(LocalContext.current)
//                                .data("")
//                                .error(R.drawable.profile_circle)
//                                .crossfade(true)
//                                .build(),
//                            contentDescription = null,
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier
////                                .clip(CircleShape)
////                                .width(24.dp)
////                                .height(24.dp)
//                                .padding(end = 8.dp)
//                        )
                        Text(
                            text = "Hi Raju",
                            style = TradeUpTypography.h2
                        )
                    }
                }
            }

//            Row(
//                modifier = Modifier
//                    .align(Alignment.CenterEnd)
//                    .clip(RoundedCornerShape(50))
//                    .border(2.dp, Green, RoundedCornerShape(50))
//                    .background(Green)
//                    .padding(start = 4.dp, end = 12.dp)
//                    .clickable { },
//                horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                TTIconScaled(
//                    icon = R.drawable.wallet_money,
//                    iconScale = IconScale.S,
//                    padding = 0.dp,
//                    tint = White
//                )
//                Text(
//                    text = "100",
//                    style = TradeUpTypography.caption,
//                    fontWeight = FontWeight.Bold,
//                    color = White
//                )
//            }

            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.End)
            ) {
                Box(contentAlignment = Alignment.CenterEnd) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            24.dp,
                            Alignment.End
                        )
                    ) {
//                        TTIconScaled(
//                            icon = R.drawable.ic_empty_wallet_m,
//                            iconScale = IconScale.S,
//                            padding = 0.dp
//                        )
//                        Text(
//                            text = viewModel.profileData.value.balance,
//                            style = TradeUpTypography.caption
//                        )
//                         Icon(
//                             modifier = Modifier.size(iconSize),
//                             imageVector = ImageVector.vectorResource(R.drawable.ic_event_filter),
//                             contentDescription = "wallet",
//                             tint = TradeUpColors.navigationBackIconColor
//                         )
                        NotificationIcon(
                            modifier = Modifier.size(iconSize),
                            unreadCount = unreadNotificationCount
                        )
//                        Icon(
//                            modifier = Modifier
//                                .size(iconSize)
//                                .clickable { navigator?.openWalletScreen() },
//                            imageVector = ImageVector.vectorResource(R.drawable.ic_empty_wallet),
//                            contentDescription = "wallet",
//                            tint = TradeUpColors.navigationBackIconColor
//                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NotificationIcon(unreadCount: Int, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = ImageVector.vectorResource(R.drawable.ic_notification),
            contentDescription = "notifications",
            tint = TradeUpColors.navigationBackIconColor
        )

        if (unreadCount > 0) {
            Surface(
                modifier = Modifier.align(Alignment.TopEnd),
                shape = CircleShape,
                color = Color.Red,
                border = BorderStroke(2.dp, Color.White)
            ) {
                Text(
                    text = "$unreadCount",
                    style = MaterialTheme.typography.caption,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    color = Color.White
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun MainScreenTopBarPreview() {
    MainScreenTopBar(
        isHomeScreen = false,
        navigator = null
    )
}