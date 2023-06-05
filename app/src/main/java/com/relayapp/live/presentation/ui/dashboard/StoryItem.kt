package com.relayapp.live.presentation.ui.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
fun StoryItem(
    onClick: () -> Unit,
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
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
    Card(
        modifier = modifier.size(height = 250.dp, width = 120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
        ),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://storage.googleapis.com/streaming-app-2b012.appspot.com/profilePictures/Abhijeet.jpg%201684042316342?GoogleAccessId=firebase-adminsdk-vvxtu%40streaming-app-2b012.iam.gserviceaccount.com&Expires=32509490400&Signature=ETk8xIZCLrLbotdKODjXuq0YtW%2FwElBYmoo8D6z6KdxEAJb3SHowfde0kiowwlU%2FPYxBfzIUS595HzB1Kz4m8SDoILsUv1w7brqVSYI7p3r5tX1K75LW%2FkmVZyiCJ531uSRLPXpdAIAFa%2BFSoRTlNmc2q4ZYWPyDylrZUgbbAfG0gbyA86%2BlBdiRXerfKBv2yPO7yaKEyocav7vN6NFcmQdd%2BaHrYWhB3hE56bW%2B5fxyEw3UoPwqEaezEa2tK6C5y3pop8URPd07KCxoYMzjN13hoy0s3Igswjc%2FltDuLaGFcdxhO5kF0y%2FZpgc2G7cN93FIvA6tPynpmoL9I2QtAg%3D%3D")
                    .error(R.drawable.profile_circle)
                    .crossfade(true)
                    .build(),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Crop,
                //colorFilter = ColorFilter.tint(Black1.copy(.5f), BlendMode.Darken),
                modifier = Modifier.fillMaxSize()
            )

            Surface(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopStart)
                    .padding(8.dp),
                border = BorderStroke(width = 1.dp, color = Color(0xFF4267B2)),
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
                text = pokemon.name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                style = MaterialTheme.typography.subtitle2.copy(
                    fontSize = 14.sp,
                    color = Color.White,
                )
            )
        }
    }
}