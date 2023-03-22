package com.example.weather.data.remote.api

import com.example.weather.base.BaseServiceTest
import com.example.weather.data.Constants
import com.example.weather.utils.factory.MockResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherApiTest : BaseServiceTest<WeatherApi>(WeatherApi::class) {

    override val baseUrl: String
        get() = Constants.OpenWeather.BASE_URL

    @Test
    fun requestLiveGetCurrentWeather() = runTest {
        val cityName = "Emeryville"
        serviceLive.getCurrentWeather(city = cityName).collect {
            assertNotNull(it)
            assertEquals(200, it.cod)
            assertEquals(cityName, it.name)
        }
    }

    @Test
    fun getLiveCurrentWeatherByLocation() = runTest {
        val latitude = 37.8313
        val longitude = -122.2852
        val cityName = "Emeryville"
        serviceLive.getCurrentWeatherByLocation(latitude = latitude, longitude = longitude)
            .collect {
                assertNotNull(it)
                assertEquals(200, it.cod)
                assertEquals(cityName, it.name)
            }
    }


    @Test
    fun requestMockGetCurrentWeather() = runTest {
        val cityName = "Emeryville"
        enqueueResponse(MockResponse.GetCurrentWeather.STATUS_200)
        serviceMock.getCurrentWeather(city = cityName).collect {
            val request = mockWebServer.takeRequest()
            assertEquals("GET", request.method)
            assertEquals(
                "/weather?q=Emeryville&units=metric&lang=en&appid=3cf14acd1832384b1f183935ac1d58cf",
                request.path
            )
        }
    }

    @Test
    fun getMockCurrentWeatherByLocation() = runTest {
        val latitude = 37.8313
        val longitude = -122.2852
        enqueueResponse(MockResponse.GetCurrentWeather.STATUS_200)
        serviceMock.getCurrentWeatherByLocation(latitude = latitude, longitude = longitude)
            .collect {
                val request = mockWebServer.takeRequest()
                assertEquals("GET", request.method)
                assertEquals(
                    "/weather?lat=37.8313&lon=-122.2852&units=metric&lang=en&appid=3cf14acd1832384b1f183935ac1d58cf",
                    request.path
                )
            }
    }
}