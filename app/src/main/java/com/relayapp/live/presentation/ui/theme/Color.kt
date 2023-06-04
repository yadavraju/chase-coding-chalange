package com.relayapp.live.presentation.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Black = Color(0xFF000000)
val Black1 = Color(0xFF090f0b)
val White = Color(0xFFFFFFFF)
val Transparent = Color(0x00000000)
val Blue = Color(0xFF252941)
val BlueDark = Color(0xFF05060B)
val Green = Color(0xFF00C805)
val LightGreen = Color(0xFF1DE9B6)
val logoGreen = Color(0xFF57BE88)
val cyan = Color(0xFF00838F)
val bb = Color(0xFF414141)
val LightThinGreen = Color(0xFFe6f3d8)
val GreyCustom = Color(0xFFEBEFF3)
val Grey50 = Color(0xFFFAFAFA)
val DarkBlue = Color(0xFF2196F3)

val CardDark = Color(0xFF3B3E43)
val CardLight = Color(0xFFF5F2F5) // White

val BackgroundLight = Color(0x00000000)
val BackgroundDark = Color(0xFF24292E)

val SettingWhite = Color(0xFFFAFAFA)
val SettingBlack = Color(0xFF111114)

val DividerLight = Color(0xFFE0E0E0)
val DividerDark = Color(0xFF6E6E6E)

val GrayCircle = Color(0xFF919191)
val RedCircle = Color(0xFFF36464)
val GreenCircle = Color(0xFF00C853)
val BorderLine = Color(0xFFE5E5EA)

val Red700 = Color(0xFFF56055)

val MediumWhite = Color(0xFFEFEEF0)
val MediumBlack = Color(0xFF2B2C2D)

val pureWhite = Color(0xFFFAFAFA)
val pureBlack = Color(0xFF111114)

val Gray = Color(0xFF939199)
val Gray25 = Color(0xFFF8F8F8)
val Gray50 = Color(0xFFF1F1F1)
val Gray75 = Color(0xFFECECEC)
val Gray100 = Color(0xFFE1E1E1)
val Gray200 = Color(0xFFEEEEEE)
val Gray300 = Color(0xFFACACAC)
val Gray400 = Color(0xFF919191)
val Gray500 = Color(0xFF6E6E6E)
val Gray600 = Color(0xFF535353)
val Gray700 = Color(0xFF616161)
val Gray800 = Color(0xFF292929)
val Gray900 = Color(0xFF212121)
val Gray950 = Color(0xFF141414)
val btnGrey = Color(0xFFCFF5F5)

val pink = Color(0xFFEE5A74)
val yellow = Color(0xFFFCB605)

val Green300 = Color(0xFF2EB688)
val Green400 = Color(0xFF145526)
val Green500 = Color(0xFF046D4A)

val Red300 = Color(0xFFF33736)
val Red400 = Color(0xFFcb290b)
val Red500 = Color(0xFF9C2221)

val Blue300 = Color(0xFF54B1DF)
val Blue500 = Color(0xFF1E3DA8)

val Yellow300 = Color(0xFFF1A22C)
val Yellow400 = Color(0xFFfaae41)
val Yellow500 = Color(0xFFCB5C0D)

val Blue400 = Color(0xFF4572E8)

val selectedBottomItemColor = Green
val unselectedBottomItemColor = Gray500

val navigationBackIconDark = White
val navigationBackIconLight = Black

val ClubhouseGreen = Color(0xFF2EAD60)
val ClubhouseLightBrown = Color(0xFFF2EEE3)
val ClubhouseDarkBrown = Color(0xFFD5CFB7)
val boarderColor1 = Color(0xffe1e1e1)

val Colors.navigationBackIconColor: Color
    @Composable get() = if (isLight) navigationBackIconLight else navigationBackIconDark

val Colors.greyButtonColor: Color
    @Composable get() = if (isLight) btnGrey else Gray300

val Colors.dividerColor: Color
    @Composable get() = if (isLight) DividerLight else DividerDark

val Colors.backgroundColor: Color
    @Composable get() = if (isLight) BackgroundLight else BackgroundDark

val Colors.yesNo: Color
    @Composable get() = if (isLight) LocalContentColor.current.copy(alpha = 0.05f) else BackgroundDark

val Colors.cardBackgroundColor: Color
    @Composable get() = if (isLight) CardLight else CardDark

val Colors.SettingCardBackgroundColor: Color
    @Composable get() = if (isLight) MediumWhite else MediumBlack

val Colors.CardDividerColor: Color
    @Composable get() = if (isLight) MediumWhite else Gray300

val Colors.PureColor: Color
    @Composable get() = if (isLight) pureWhite else pureBlack

val Colors.boarderColor: Color
    @Composable get() = if (isLight) ClubhouseLightBrown else CardDark

data class Gradient(
    val startColor: Color,
    val endColor: Color
) {
    companion object {
        fun from(startColor: Int, endColor: Int? = null) = Gradient(
            startColor = startColor.toComposeColor(),
            endColor = (endColor ?: startColor).toComposeColor()
        )

        fun solid(color: Color) = Gradient(color, color)

        @Composable
        fun black() = Gradient(Gray, TradeUpColors.PureColor)
    }

    fun asHorizontalBrush() = Brush.horizontalGradient(colors = listOf(startColor, endColor))

    fun asVerticalBrush() = Brush.verticalGradient(colors = listOf(startColor, endColor))
}

fun Int.toComposeColor() = Color(this)
