package com.relayapp.live.presentation.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = BackgroundDark,
    primaryVariant = BackgroundDark,
    onPrimary = White,
    secondary = Green,
    secondaryVariant = Green,
    onSecondary = Black,
    background = BackgroundDark,
    onBackground = BackgroundDark,
    surface = CardDark,
    onSurface = CardDark
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = Green,
    onPrimary = Black,
    secondary = Green,
    secondaryVariant = Green,
    onSecondary = Black,
    background = BackgroundLight,
    onBackground = BackgroundLight,
    surface = White,
    onSurface = White
)

val TradeUpColors: Colors
    @Composable get() = MaterialTheme.colors

val TradeUpShapes: Shapes
    @Composable get() = MaterialTheme.shapes

val TradeUpTypography: Typography
    @Composable get() = MaterialTheme.typography


@Composable
fun RelayTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val typography = if (darkTheme) {
        DarkTypography
    } else {
        LightTypography
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}
