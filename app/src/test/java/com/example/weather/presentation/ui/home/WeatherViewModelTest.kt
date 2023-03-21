package com.example.weather.presentation.ui.home

import app.cash.turbine.test
import com.example.weather.base.MockkUnitTest
import com.example.weather.data.model.CurrentWeather
import com.example.weather.domain.usecase.weather.GetCurrentWeatherByCityUseCase
import com.example.weather.domain.usecase.weather.GetHourlyWeatherUseCase
import com.example.weather.domain.usecase.weather.GetLastCityUseCase
import com.example.weather.presentation.model.CurrentWeatherMapper
import com.example.weather.presentation.model.HourlyWeatherMapper
import com.example.weather.utils.toFlow
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WeatherViewModelTest :  MockkUnitTest() {

    @MockK
    lateinit var getCurrentWeatherByCityUseCase: GetCurrentWeatherByCityUseCase

    @MockK
    lateinit var weatherMapper: CurrentWeatherMapper

    @MockK
    lateinit var getLastCityUseCase: GetLastCityUseCase

    @MockK
    lateinit var getHourlyWeatherUseCase: GetHourlyWeatherUseCase

    @MockK
    lateinit var hourlyWeatherMapper: HourlyWeatherMapper

    private lateinit var testObject: WeatherViewModel

    @Before
    override fun onCreate() {
        testObject = spyk(
            WeatherViewModel(
                getCurrentWeatherByCityUseCase,
                weatherMapper,
                getLastCityUseCase,
                getHourlyWeatherUseCase,
                hourlyWeatherMapper
            ),
            recordPrivateCalls = true,
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getWeatherByCityName success`() = runTest {
        val currentWeather = mockk<CurrentWeather>(relaxed = true)
        every { getCurrentWeatherByCityUseCase.invoke(any()) } returns currentWeather.toFlow()

        testObject.getWeather("")

        verify (exactly = 1) {
            weatherMapper.mapperToViewDataModel(any())
        }
        testObject.state.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(HomeViewState::class.java)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onSearchInputChanged() = runTest {
        testObject.onSearchInputChanged("Kathmandu")

        testObject.state.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(HomeViewState::class.java)
                assertEquals("Kathmandu", this.searchState.query)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun enableSearchView() = runTest {
        testObject.enableSearchView(true)

        testObject.state.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(HomeViewState::class.java)
                assertTrue(this.searchState.enabled)
            }
        }
    }
}