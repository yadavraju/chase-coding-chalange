package com.relayapp.live.data.model

import com.relayapp.live.data.base.DataModel
import com.google.gson.annotations.SerializedName

data class WeatherItem(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String?,
    @SerializedName("icon") val icon: String?
) : DataModel()
