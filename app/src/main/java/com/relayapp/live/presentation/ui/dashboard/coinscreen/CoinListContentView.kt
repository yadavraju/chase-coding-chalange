package com.relayapp.live.presentation.ui.dashboard.coinscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.relayapp.live.R
import com.relayapp.live.components.IconScale
import com.relayapp.live.components.TTIconScaled
import com.relayapp.live.data.model.coinresponse.CoinData
import com.relayapp.live.presentation.ui.dashboard.viewmodel.CoinViewSate
import com.relayapp.live.presentation.ui.theme.Blue300
import com.relayapp.live.presentation.ui.theme.Blue500
import com.relayapp.live.presentation.ui.theme.Green300
import com.relayapp.live.presentation.ui.theme.Green500
import com.relayapp.live.presentation.ui.theme.Red300
import com.relayapp.live.presentation.ui.theme.Red500
import com.relayapp.live.presentation.ui.theme.Yellow300
import com.relayapp.live.presentation.ui.theme.Yellow500
import com.relayapp.live.presentation.ui.theme.yellow

@Composable
fun CoinListContentView(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    selectItem: (CoinData) -> Unit = {},
    viewState: CoinViewSate,
    coinItems: List<CoinData>
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = false),
        onRefresh = { },
        indicatorPadding = paddingValues,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true
            )
        },
        content = {
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(coinItems, key = { it.coins }) { coin ->
                    CoinContentItemView(modifier = modifier, coinData = coin)
                }
            }
        })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinContentItemView(
    modifier: Modifier = Modifier,
    coinData: CoinData,
    onDetailClick: () -> Unit = {}
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
        onClick = onDetailClick,
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.Transparent,
        border = BorderStroke(1.dp, brush),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(Modifier.width(8.dp))
            TTIconScaled(
                icon = R.drawable.wallet_money,
                tint = yellow,
                iconScale = IconScale.XS,
                padding = 0.dp
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "${coinData.coins}",
                style = MaterialTheme.typography.overline.copy(fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
            )
            Spacer(Modifier.weight(1f))

            Text(
                text = "$${coinData.price}",
                style = MaterialTheme.typography.overline.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(8.dp))
        }
    }
}