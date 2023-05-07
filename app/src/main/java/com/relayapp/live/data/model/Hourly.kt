package com.relayapp.live.data.model

import com.relayapp.live.data.base.DataModel
import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("dt") val dt: Long,
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("dew_point") val dewPoint: Double,
    @SerializedName("uvi") val uvi: Double,
    @SerializedName("clouds") val clouds: Double,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_deg") val windDeg: Int,
    @SerializedName("wind_gust") val windGust: Double,
    @SerializedName("weather") val weather: List<WeatherItem>,
    @SerializedName("pop") val pop: Double
) : DataModel()
