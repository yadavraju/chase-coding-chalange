package com.relayapp.live.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

const val SEARCH_SCREEN = "SEARCH_SCREEN"

@Composable
fun EmptyView(modifier: Modifier = Modifier, screenType: String? = null) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = if (screenType == SEARCH_SCREEN) {
                "Search Event by their title, \n keyword, category etc."
            } else {
                "No data found"
            },
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
    }
}

