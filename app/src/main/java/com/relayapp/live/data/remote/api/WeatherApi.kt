package com.relayapp.live.data.remote.api

import com.relayapp.live.data.Constants
import com.relayapp.live.data.model.CurrentWeather
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en",
        @Query("appid") appId: String = Constants.OpenWeather.YEK_IPA
    ): Flow<CurrentWeather>

    @GET("weather")
    suspend fun getCurrentWeather1(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en",
        @Query("appid") appId: String = Constants.OpenWeather.YEK_IPA
    ): CurrentWeather

    @GET("weather")
    fun getCurrentWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en",
        @Query("appid") appId: String = Constants.OpenWeather.YEK_IPA,
    ): Flow<CurrentWeather>
}