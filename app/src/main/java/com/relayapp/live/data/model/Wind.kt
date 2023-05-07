package com.relayapp.live.data.model

import com.relayapp.live.data.base.DataModel
import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Double,
    @SerializedName("gust") val gust: Double
) : DataModel()
