package com.relayapp.live.domain.usecase.weather

import android.content.Context
import com.relayapp.live.R
import com.relayapp.live.data.model.CurrentWeather
import com.relayapp.live.domain.asFlow
import com.relayapp.live.domain.exception.BaseException
import com.relayapp.live.domain.repository.WeatherRepository
import com.relayapp.live.domain.usecase.base.UseCase
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
