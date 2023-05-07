package com.relayapp.live.domain.repository

import com.relayapp.live.data.model.CurrentWeather
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getCurrentWeather(city: String): Flow<CurrentWeather>

    fun getCurrentWeatherByLocation(latLng: LatLng): Flow<CurrentWeather>
}
