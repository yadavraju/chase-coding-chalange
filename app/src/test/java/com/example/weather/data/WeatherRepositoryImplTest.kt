package com.example.weather.data

import com.example.weather.base.MockkUnitTest
import com.example.weather.data.local.pref.PrefsHelper
import com.example.weather.data.remote.api.WeatherApi
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.utils.factory.ModelDefault
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test

class WeatherRepositoryImplTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var weatherApi: WeatherApi

    @MockK(relaxed = true)
    lateinit var prefsHelper: PrefsHelper

    private lateinit var testObject: WeatherRepository

    override fun onCreate() {
        super.onCreate()
        testObject = WeatherRepositoryImpl(weatherApi, prefsHelper)
    }

    @Test
    fun getCurrentWeather() = runTest {
        val city = "Kathmandu"
        testObject.getCurrentWeather(city).map {
            coVerify { prefsHelper.saveLastCity(city) }
            assertNotNull(it)
        }
    }

    @Test
    fun getCurrentWeatherByLocation() = runTest {
        testObject.getCurrentWeatherByLocation(ModelDefault.latLng()).map {
            assertNotNull(it)
        }
    }
}