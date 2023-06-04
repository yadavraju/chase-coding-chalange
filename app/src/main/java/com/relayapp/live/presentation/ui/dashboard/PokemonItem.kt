package com.relayapp.live.presentation.ui.dashboard

import android.graphics.drawable.DrawableWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.relayapp.live.R
import com.relayapp.live.presentation.model.Pokemon
import com.relayapp.live.presentation.ui.theme.Black
import com.relayapp.live.presentation.ui.theme.Black1
import com.relayapp.live.presentation.ui.theme.Blue300
import com.relayapp.live.presentation.ui.theme.Blue500
import com.relayapp.live.presentation.ui.theme.Green300
import com.relayapp.live.presentation.ui.theme.Green500
import com.relayapp.live.presentation.ui.theme.Red300
import com.relayapp.live.presentation.ui.theme.Red500
import com.relayapp.live.presentation.ui.theme.Yellow300
import com.relayapp.live.presentation.ui.theme.Yellow500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PokemonItem(
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
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(brush = brush, alpha = .4f)
                .padding(10.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://storage.googleapis.com/streaming-app-2b012.appspot.com/profilePictures/Abhijeet.jpg%201684042316342?GoogleAccessId=firebase-adminsdk-vvxtu%40streaming-app-2b012.iam.gserviceaccount.com&Expires=32509490400&Signature=ETk8xIZCLrLbotdKODjXuq0YtW%2FwElBYmoo8D6z6KdxEAJb3SHowfde0kiowwlU%2FPYxBfzIUS595HzB1Kz4m8SDoILsUv1w7brqVSYI7p3r5tX1K75LW%2FkmVZyiCJ531uSRLPXpdAIAFa%2BFSoRTlNmc2q4ZYWPyDylrZUgbbAfG0gbyA86%2BlBdiRXerfKBv2yPO7yaKEyocav7vN6NFcmQdd%2BaHrYWhB3hE56bW%2B5fxyEw3UoPwqEaezEa2tK6C5y3pop8URPd07KCxoYMzjN13hoy0s3Igswjc%2FltDuLaGFcdxhO5kF0y%2FZpgc2G7cN93FIvA6tPynpmoL9I2QtAg%3D%3D")
                        .error(R.drawable.profile_circle)
                        .crossfade(true)
                        .build(),
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Fit,
                    //colorFilter = ColorFilter.tint(Black1.copy(.5f), BlendMode.Darken),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.2f)
                        .fillMaxHeight()
                )
            }

            Spacer(Modifier.height(14.dp))

            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.alpha(.8f)
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = pokemon.numberString,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.alpha(.4f)
            )
        }
    }
}