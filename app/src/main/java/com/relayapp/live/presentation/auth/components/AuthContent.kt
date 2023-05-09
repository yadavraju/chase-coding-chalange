package com.relayapp.live.presentation.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.relayapp.live.presentation.ui.theme.TradeUpTypography

@Composable
fun AuthContent(
    padding: PaddingValues,
    oneTapSignIn: () -> Unit
) {
    Column(verticalArrangement = Arrangement.Center) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = "Welcome to Relay Streaming App",
            fontSize = TradeUpTypography.h3.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.BottomCenter
        ) {
            SignInButton(
                onClick = oneTapSignIn
            )
        }
    }

}