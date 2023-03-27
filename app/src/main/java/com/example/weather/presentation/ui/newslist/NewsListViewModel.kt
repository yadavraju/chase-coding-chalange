package com.example.weather.presentation.ui.newslist

import com.example.weather.domain.exception.BaseException
import com.example.weather.presentation.base.BaseViewModel
import com.example.weather.presentation.base.ViewState
import com.example.weather.presentation.model.DummyNewList
import com.example.weather.presentation.model.NewsListViewModelData
import com.example.weather.presentation.ui.home.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class NewsListViewState(
    override val isLoading: Boolean = false,
    override val exception: BaseException? = null,
    val newsViewModelData: List<NewsListViewModelData>? = null,
    val searchState: SearchState = SearchState.Closed()
) : ViewState(isLoading, exception)

class NewsListViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(NewsListViewState(isLoading = true))

    override val state: StateFlow<NewsListViewState>
        get() = _state

    init {
        _state.update {
            it.copy(isLoading = false, newsViewModelData = DummyNewList())
        }
    }
}