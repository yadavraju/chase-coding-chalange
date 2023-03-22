package com.example.weather.data

import androidx.compose.ui.text.intl.Locale
import com.example.weather.data.local.pref.PrefsHelper
import com.example.weather.data.model.CurrentWeather
import com.example.weather.data.remote.api.WeatherApi
import com.example.weather.domain.repository.WeatherRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val prefsHelper: PrefsHelper
) : WeatherRepository {

    override fun getCurrentWeather(city: String): Flow<CurrentWeather> {
        return weatherApi.getCurrentWeather(city = city)
            .map { weather ->
                prefsHelper.saveLastCity(city)
                weather
            }
    }

    override fun getCurrentWeatherByLocation(latLng: LatLng): Flow<CurrentWeather> =
        weatherApi.getCurrentWeatherByLocation(
            latitude = latLng.latitude,
            longitude = latLng.longitude,
            lang = Locale.current.language
        )
}
