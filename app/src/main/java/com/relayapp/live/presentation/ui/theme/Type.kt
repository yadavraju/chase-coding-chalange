package com.relayapp.live.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val DarkTypography = Typography(
    h1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 21.sp
    ),
    h3 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.SemiBold,
        color = White,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = White,
        fontSize = 15.sp
    ),
    h5 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = White,
        fontSize = 18.sp
    ),
    h6 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Normal,
        color = White,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = White,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        color = White,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)

// set of light material typography styles to start with.
val LightTypography = Typography(
    h1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = 21.sp
    ),
    h3 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.SemiBold,
        color = Black,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = Black,
        fontSize = 15.sp
    ),
    h5 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = Black,
        fontSize = 18.sp
    ),
    h6 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = 15.sp
    ),
    body1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Normal,
        color = Black,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = Black,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        color = Black,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Black,
        fontSize = 12.sp
    )
)

//import androidx.compose.material.Typography
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.sp
//import com.relayapp.live.R
//
//private val Montserrat = FontFamily(
//    Font(R.font.montserrat_thin, FontWeight.Thin),
//    Font(R.font.montserrat_light, FontWeight.Light),
//    Font(R.font.montserrat_extralight, FontWeight.ExtraLight),
//    Font(R.font.montserrat_regular, FontWeight.Normal),
//    Font(R.font.montserrat_medium, FontWeight.Medium),
//    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
//    Font(R.font.montserrat_bold, FontWeight.Bold),
//    Font(R.font.montserrat_extrabold, FontWeight.ExtraBold),
//    Font(R.font.montserrat_black, FontWeight.Black)
//)
//
//val WeatherTypography = Typography(
//    h1 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 96.sp,
//        fontWeight = FontWeight.Light,
//        lineHeight = 117.sp,
//        letterSpacing = (-1.5).sp
//    ),
//    h2 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 60.sp,
//        fontWeight = FontWeight.Light,
//        lineHeight = 73.sp,
//        letterSpacing = (-0.5).sp
//    ),
//    h3 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 48.sp,
//        fontWeight = FontWeight.Normal,
//        lineHeight = 59.sp
//    ),
//    h4 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 30.sp,
//        fontWeight = FontWeight.SemiBold,
//        lineHeight = 37.sp
//    ),
//    h5 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 24.sp,
//        fontWeight = FontWeight.SemiBold,
//        lineHeight = 29.sp
//    ),
//    h6 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 20.sp,
//        fontWeight = FontWeight.SemiBold,
//        lineHeight = 24.sp
//    ),
//    subtitle1 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 16.sp,
//        fontWeight = FontWeight.SemiBold,
//        lineHeight = 20.sp,
//        letterSpacing = 0.5.sp
//    ),
//    subtitle2 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 14.sp,
//        fontWeight = FontWeight.Medium,
//        lineHeight = 17.sp,
//        letterSpacing = 0.1.sp
//    ),
//    body1 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Medium,
//        lineHeight = 20.sp,
//        letterSpacing = 0.15.sp
//    ),
//    body2 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 14.sp,
//        fontWeight = FontWeight.SemiBold,
//        lineHeight = 20.sp,
//        letterSpacing = 0.25.sp
//    ),
//    button = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 14.sp,
//        fontWeight = FontWeight.SemiBold,
//        lineHeight = 16.sp,
//        letterSpacing = 1.25.sp
//    ),
//    caption = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 12.sp,
//        fontWeight = FontWeight.SemiBold,
//        lineHeight = 16.sp,
//        letterSpacing = 0.sp
//    ),
//    overline = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 12.sp,
//        fontWeight = FontWeight.SemiBold,
//        lineHeight = 16.sp,
//        letterSpacing = 1.sp
//    )
//)
