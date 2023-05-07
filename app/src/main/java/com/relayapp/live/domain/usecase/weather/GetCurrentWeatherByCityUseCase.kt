package com.relayapp.live.domain.usecase.weather

import android.content.Context
import com.relayapp.live.R
import com.relayapp.live.data.model.CurrentWeather
import com.relayapp.live.domain.asFlow
import com.relayapp.live.domain.exception.BaseException
import com.relayapp.live.domain.repository.WeatherRepository
import com.relayapp.live.domain.usecase.UseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherByCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    @ApplicationContext private val context: Context
) : UseCase<GetCurrentWeatherByCityUseCase.Params, CurrentWeather>() {

    override fun execute(params: Params?): Flow<CurrentWeather> {
        if (params?.city?.isNotEmpty() == true) {
            return weatherRepository.getCurrentWeather(params.city)
        }
        return BaseException.AlertException(-1, context.getString(R.string.city_input_invalid))
            .asFlow()
    }

    data class Params(val city: String)
}
