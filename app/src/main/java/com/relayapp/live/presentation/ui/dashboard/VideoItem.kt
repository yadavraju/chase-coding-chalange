package com.relayapp.live.presentation.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.relayapp.live.R
import com.relayapp.live.presentation.model.Pokemon
import com.relayapp.live.presentation.ui.theme.Black1
import com.relayapp.live.presentation.ui.theme.Blue300
import com.relayapp.live.presentation.ui.theme.Blue500
import com.relayapp.live.presentation.ui.theme.Green300
import com.relayapp.live.presentation.ui.theme.Green500
import com.relayapp.live.presentation.ui.theme.Red300
import com.relayapp.live.presentation.ui.theme.Red500
import com.relayapp.live.presentation.ui.theme.Yellow300
import com.relayapp.live.presentation.ui.theme.Yellow500

@Composable
internal fun VideoItem(
    onClick: () -> Unit,
    pokemon: Pokemon,
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
                    .data("https://storage.googleapis.com/streaming-app-2b012.appspot.com/profilePictures/Abhijeet.jpg%201684042316342?GoogleAccessId=firebase-adminsdk-vvxtu%40streaming-app-2b012.iam.gserviceaccount.com&Expires=32509490400&Signature=ETk8xIZCLrLbotdKODjXuq0YtW%2FwElBYmoo8D6z6KdxEAJb3SHowfde0kiowwlU%2FPYxBfzIUS595HzB1Kz4m8SDoILsUv1w7brqVSYI7p3r5tX1K75LW%2FkmVZyiCJ531uSRLPXpdAIAFa%2BFSoRTlNmc2q4ZYWPyDylrZUgbbAfG0gbyA86%2BlBdiRXerfKBv2yPO7yaKEyocav7vN6NFcmQdd%2BaHrYWhB3hE56bW%2B5fxyEw3UoPwqEaezEa2tK6C5y3pop8URPd07KCxoYMzjN13hoy0s3Igswjc%2FltDuLaGFcdxhO5kF0y%2FZpgc2G7cN93FIvA6tPynpmoL9I2QtAg%3D%3D")
                    .error(R.drawable.profile_circle)
                    .crossfade(true)
                    .build(),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Black1.copy(.5f), BlendMode.Darken),
                modifier = modifier
                    .width(180.dp)
                    .height(250.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "John Doe",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "2 hours ago",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}