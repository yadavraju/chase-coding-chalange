package com.example.weather.domain.usecase.weather

import android.content.Context
import com.example.weather.R
import com.example.weather.data.model.CurrentWeather
import com.example.weather.domain.asFlow
import com.example.weather.domain.exception.BaseException
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.usecase.UseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentWeatherByLocationUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherRepository: WeatherRepository,
) : UseCase<GetCurrentWeatherByLocationUseCase.Params, CurrentWeather>() {

    override fun execute(params: Params?): Flow<CurrentWeather> = if (params != null) {
        weatherRepository.getCurrentWeatherByLocation(params.latLng)
    } else {
        BaseException.SnackBarException(message = context.getString(R.string.error_message_lat_lng_are_invalid))
            .asFlow()
    }

    data class Params(
        val latLng: LatLng,
    )
}
