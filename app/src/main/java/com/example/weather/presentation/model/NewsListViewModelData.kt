package com.example.weather.presentation.model

import com.example.weather.presentation.base.ViewDataModel
import javax.annotation.concurrent.Immutable

@Immutable
data class NewsListViewModelData(
    val newsId: Int,
    val newsTitle: String
) : ViewDataModel()

fun DummyNewList() = listOf(
    NewsListViewModelData(1, "something news"),
    NewsListViewModelData(1, "something news1"),
    NewsListViewModelData(1, "something news3"),
    NewsListViewModelData(1, "something news3")
)