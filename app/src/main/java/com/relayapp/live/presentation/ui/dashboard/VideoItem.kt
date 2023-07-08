package com.relayapp.live.presentation.ui.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.relayapp.live.R
import com.relayapp.live.data.model.liveresponse.RoomResult
import com.relayapp.live.presentation.ui.theme.Black1
import com.relayapp.live.presentation.ui.theme.Blue300
import com.relayapp.live.presentation.ui.theme.Blue500
import com.relayapp.live.presentation.ui.theme.Green300
import com.relayapp.live.presentation.ui.theme.Green500
import com.relayapp.live.presentation.ui.theme.Red300
import com.relayapp.live.presentation.ui.theme.Red500
import com.relayapp.live.presentation.ui.theme.TradeUpTypography
import com.relayapp.live.presentation.ui.theme.Yellow300
import com.relayapp.live.presentation.ui.theme.Yellow500

@Composable
internal fun VideoItem(
    onClick: () -> Unit,
    roomResult: RoomResult,
    modifier: Modifier = Modifier
) {

    val brush = remember {
        Brush.linearGradient(
            listOf(
                listOf(
                    Red300,
                    Red500,
                ),
                listOf(
                    Yellow300,
                    Yellow500,
                ),
                listOf(
                    Green300,
                    Green500,
                ),
                listOf(
                    Blue300,
                    Blue500,
                ),
            ).random()
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            modifier = modifier
                .width(180.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(brush = brush, alpha = .4f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(roomResult.streamerId.profilePicture)
                    .crossfade(true)
                    .build(),
                contentDescription = roomResult.streamerId.stageName,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Black1.copy(.5f), BlendMode.Darken),
                modifier = modifier
                    .width(180.dp)
                    .height(250.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp),
                    border = BorderStroke(width = 1.dp, color = Yellow300),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_circle),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(
                    text = roomResult.streamerId.stageName,
                    color = Color.White,
                    style = TradeUpTypography.subtitle2.copy(fontWeight = FontWeight.Medium),
                )
            }

//            Column(
//                modifier = Modifier
//                    .fillMaxSize(),
//                verticalArrangement = Arrangement.Bottom
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Surface(
//                        modifier = Modifier
//                            .size(40.dp)
//                            .padding(8.dp),
//                        border = BorderStroke(width = 1.dp, color = Gray.copy(.5f)),
//                        shape = CircleShape
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.profile_circle),
//                            contentDescription = null,
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.fillMaxSize()
//                        )
//                    }
//                    Text(
//                        text = "John Doe",
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 14.sp
//                    )
//                }
//            }
//            Text(
//                text = pokemon.name,
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//                    .padding(8.dp),
//                style = MaterialTheme.typography.subtitle2.copy(
//                    fontSize = 14.sp,
//                    color = Color.White,
//                )
//            )
        }
    }
}