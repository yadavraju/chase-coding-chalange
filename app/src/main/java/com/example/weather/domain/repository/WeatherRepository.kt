package com.example.weather.domain.repository

import com.example.weather.data.model.CurrentWeather
import com.example.weather.data.model.Daily
import com.example.weather.data.model.Hourly
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getCurrentWeather(city: String): Flow<CurrentWeather>

    fun getCurrentWeatherByLocation(latLng: LatLng): Flow<CurrentWeather>
}
