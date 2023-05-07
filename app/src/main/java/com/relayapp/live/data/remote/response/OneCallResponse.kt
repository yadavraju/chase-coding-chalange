package com.relayapp.live.data.remote.response

import com.relayapp.live.data.model.Current
import com.relayapp.live.data.model.Daily
import com.relayapp.live.data.model.Hourly
import com.google.gson.annotations.SerializedName

data class OneCallResponse(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_offset") val timezoneOffset: Int,
    @SerializedName("current") val current: Current,
    @SerializedName("hourly") val hourly: List<Hourly>?,
    @SerializedName("daily") val daily: List<Daily>?
)
