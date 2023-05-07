package com.relayapp.live.presentation.ui.home

import app.cash.turbine.test
import com.relayapp.live.base.MockkUnitTest
import com.relayapp.live.data.model.CurrentWeather
import com.relayapp.live.domain.usecase.weather.GetCurrentLocationUseCase
import com.relayapp.live.domain.usecase.weather.GetCurrentWeatherByCityUseCase
import com.relayapp.live.domain.usecase.weather.GetCurrentWeatherByLocationUseCase
import com.relayapp.live.domain.usecase.weather.GetLastCityUseCase
import com.relayapp.live.presentation.model.CurrentWeatherMapper
import com.relayapp.live.utils.callPrivateFunction
import com.relayapp.live.utils.factory.ModelDefault
import com.relayapp.live.utils.toFlow
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest : MockkUnitTest() {

    @MockK
    lateinit var getCurrentWeatherByCityUseCase: GetCurrentWeatherByCityUseCase

    @MockK
    lateinit var weatherMapper: CurrentWeatherMapper

    @MockK
    lateinit var getLastCityUseCase: GetLastCityUseCase

    @MockK
    lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase

    @MockK
    lateinit var getCurrentWeatherByLocationUseCase: GetCurrentWeatherByLocationUseCase

    private lateinit var testObject: WeatherViewModel

    @Before
    override fun onCreate() {
        testObject = spyk(
            WeatherViewModel(
                getCurrentWeatherByCityUseCase,
                getCurrentLocationUseCase,
                getCurrentWeatherByLocationUseCase,
                weatherMapper,
                getLastCityUseCase
            ),
            recordPrivateCalls = true,
        )
    }

    @Test
    fun `getWeatherByCityName success`() = runTest {
        val currentWeather = mockk<CurrentWeather>(relaxed = true)
        every { getCurrentWeatherByCityUseCase.invoke(any()) } returns currentWeather.toFlow()

        testObject.getWeather("")

        verify(exactly = 1) {
            weatherMapper.mapperToViewDataModel(any())
        }
        testObject.state.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(HomeViewState::class.java)
            }
        }
    }

    @Test
    fun `getCurrentLocation success`() = runTest() {
        every { getCurrentLocationUseCase() } returns flow { emit(ModelDefault.latLng()) }

        testObject.getCurrentLocation()

        verify(exactly = 1) {
            testObject["showLoading"]()
            testObject["getWeatherByLatLang"](ModelDefault.latLng())
        }
    }


    @Test
    fun `getCurrentWeather success with current location `() = runTest {
        val currentWeather = mockk<CurrentWeather>(relaxed = true)
        every { getCurrentWeatherByLocationUseCase(any()) } returns currentWeather.toFlow()

        testObject.callPrivateFunction<Unit>("getWeatherByLatLang", ModelDefault.latLng())

        verify(exactly = 1) {
            weatherMapper.mapperToViewDataModel(any())
        }
        testObject.state.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(HomeViewState::class.java)
            }
        }
    }

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

    @Test
    fun showLoading() = runTest {
        testObject.callPrivateFunction<Unit>("showLoading")

        testObject.state.test {
            awaitItem().apply {
                assertTrue(this.isLoading)
            }
        }
    }

    @Test
    fun hideLoading() = runTest {
        testObject.hideLoading()

        testObject.state.test {
            awaitItem().apply {
                assertFalse(this.isLoading)
            }
        }
    }

    @Test
    fun permissionIsNotGranted() = runTest {
        testObject.permissionIsNotGranted()
        testObject.state.test {
            awaitItem().apply {
                assertFalse(this.isLoading)
                assertNotNull(this.exception)
            }
        }
    }

    @Test
    fun cleanEvent() = runTest {
        testObject.cleanEvent()

        testObject.state.test {
            awaitItem().apply {
                assertFalse(this.isRequestPermission)
            }
        }
    }

}