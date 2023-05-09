package com.relayapp.live.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.relayapp.live.core.jetframework.Delayed
import com.relayapp.live.presentation.ui.theme.SettingCardBackgroundColor
import com.relayapp.live.presentation.ui.theme.TradeUpColors

@Composable
fun LoadingView(modifier: Modifier = Modifier, delayMillis: Long = 100L) {
    Delayed(delayMillis = delayMillis) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = when (modifier == Modifier) {
                true -> Modifier.fillMaxSize()
                false -> modifier
            }
        ) {
            ProgressIndicator()
        }
    }
}

@Composable
fun ProgressDialog(dialogState: Boolean) {
    if (dialogState) {
        Dialog(
            onDismissRequest = { },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        TradeUpColors.SettingCardBackgroundColor,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                ProgressIndicator()
            }
        }
    }
}
