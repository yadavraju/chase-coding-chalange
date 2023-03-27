package com.example.weather.presentation.ui.newslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.R
import com.example.weather.presentation.model.NewsListViewModelData
import com.example.weather.presentation.ui.custom.CommonToolbarScreen
import com.example.weather.presentation.ui.custom.FullScreenLoading

@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val viewState by viewModel.state.collectAsState()

    when {
        viewState.isLoading -> FullScreenLoading()
        else -> NewsListScreenContent(scaffoldState, viewState.newsViewModelData)
    }
}

@Composable
fun NewsListScreenContent(
    scaffoldState: ScaffoldState,
    newsViewModelData: List<NewsListViewModelData>?,
) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                CommonToolbarScreen(
                    hamburgerNavigationClicked = {},
                    openSearchView = {}
                )
            }
        ) {
            LazyColumn(
                contentPadding = it,
                modifier = Modifier.padding(8.dp)
            ) {
                newsViewModelData?.let { newsList ->
                    items(newsList) { news ->
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),

                            ) {

                            Row(modifier = Modifier.padding(4.dp)) {
                                Image(
                                    painter = painterResource(R.drawable.bg_current),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .padding(8.dp),
                                    contentScale = ContentScale.FillBounds,
                                    alpha = 0.2f
                                )
                                Text(
                                    modifier = Modifier.padding(top = 8.dp),
                                    text = news.newsTitle
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}
