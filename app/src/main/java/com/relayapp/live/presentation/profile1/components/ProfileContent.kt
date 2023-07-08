package com.relayapp.live.presentation.profile1.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.relayapp.live.R
import com.relayapp.live.components.IconScale
import com.relayapp.live.components.TTIconScaled
import com.relayapp.live.presentation.ui.dashboard.RoomGridView
import com.relayapp.live.presentation.ui.theme.Gray
import com.relayapp.live.presentation.ui.theme.Green
import com.relayapp.live.presentation.ui.theme.PureColor
import com.relayapp.live.presentation.ui.theme.SettingCardBackgroundColor
import com.relayapp.live.presentation.ui.theme.TradeUpColors
import com.relayapp.live.presentation.ui.theme.TradeUpTypography
import com.relayapp.live.presentation.ui.theme.navigationBackIconColor

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    photoUrl: String,
    displayName: String,
    email: String,
    onLogout: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = 8.dp, bottom = 16.dp)
            .testTag("settings_lazy_column"),
    ) {
        AccountCard(
            photoUrl = photoUrl,
            displayName = displayName,
            email = email,
            verificationClick = {},
            onLogout = { onLogout.invoke() }
        )
        Spacer(Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = stringResource(R.string.video_uppercase),
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Black,
                color = Gray
            ),
        )
        Spacer(Modifier.height(8.dp))
        RoomGridView(
            onPokemonClicked = { _ -> },
            isLoading = false,
            roomType = "getAllPublicRooms"
        )
    }
}

@Composable
private fun AccountCard(
    photoUrl: String,
    displayName: String,
    email: String,
    verificationClick: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(TradeUpColors.SettingCardBackgroundColor, RoundedCornerShape(24.dp))
    ) {
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("settings_profile_card"),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(24.dp))
            Text(
                text = stringResource(R.string.account_uppercase),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Black,
                    color = Gray
                ),
            )
            Spacer(Modifier.weight(1f))
            AccountCardButton(
                icon = R.drawable.ic_logout,
                text = stringResource(R.string.logout),
            ) {
                onLogout()
            }
            Spacer(Modifier.width(16.dp))
        }
        AccountCardUser(
            photoUrl = photoUrl,
            displayName = displayName,
            email = email,
            verificationClick = verificationClick
        )
    }
}

@Composable
private fun AccountCardUser(
    photoUrl: String,
    displayName: String,
    email: String,
    verificationClick: () -> Unit
) {
    Spacer(Modifier.height(4.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(20.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = Crop,
            modifier = Modifier
                .clip(CircleShape)
                .width(26.dp)
                .height(26.dp)
        )
//        TTIconScaled(
//            icon = R.drawable.ic_user_square,
//            iconScale = IconScale.XS,
//            padding = 0.dp
//        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = displayName,
            style = TradeUpTypography.h6.copy(
                fontWeight = FontWeight.ExtraBold,
                color = TradeUpColors.navigationBackIconColor
            )
        )
        Spacer(Modifier.width(24.dp))
    }
    Spacer(Modifier.height(12.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(20.dp))
        TTIconScaled(
            icon = R.drawable.ic_email,
            iconScale = IconScale.XS,
            padding = 0.dp
        )

        Spacer(Modifier.width(12.dp))
        Text(
            text = "${email}",
            style = TradeUpTypography.h6.copy(
                fontWeight = FontWeight.ExtraBold,
                color = TradeUpColors.navigationBackIconColor
            )
        )
        Spacer(Modifier.width(24.dp))
    }
    Spacer(Modifier.height(12.dp))

    Row(
        modifier = Modifier.clickable { verificationClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(20.dp))
        TTIconScaled(
            icon = R.drawable.ic_data_synced,
            tint = Green,
            iconScale = IconScale.XS,
            padding = 0.dp
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = stringResource(R.string.verified),
            style = TradeUpTypography.h6.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Green
            )
        )
        Spacer(Modifier.width(24.dp))
    }

    Spacer(Modifier.height(24.dp))
}

@Composable
private fun AccountCardButton(
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(percent = 50))
            .background(TradeUpColors.PureColor, RoundedCornerShape(percent = 50))
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(12.dp))
        TTIconScaled(
            icon = icon,
            iconScale = IconScale.M
        )
        Spacer(Modifier.width(4.dp))
        Text(
            modifier = Modifier
                .padding(vertical = 10.dp),
            text = text,
            style = TradeUpTypography.h6.copy(
                fontWeight = FontWeight.Bold,
                color = TradeUpColors.navigationBackIconColor
            )
        )

        Spacer(Modifier.width(24.dp))
    }
}