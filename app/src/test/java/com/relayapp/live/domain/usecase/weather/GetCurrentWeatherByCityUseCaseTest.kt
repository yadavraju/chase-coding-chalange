package com.relayapp.live.domain.usecase.weather

import android.content.Context
import com.relayapp.live.base.MockkUnitTest
import com.relayapp.live.domain.repository.WeatherRepository
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCurrentWeatherByCityUseCaseTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var repository: WeatherRepository

    @MockK(relaxed = true)
    lateinit var context: Context

    @SpyK
    @InjectMockKs
    private lateinit var testObject: GetCurrentWeatherByCityUseCase

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val params = GetCurrentWeatherByCityUseCase.Params("Kathmandu")

        // Act (When)
        testObject.invoke(params)

        // Assert (Then)
        coVerify { testObject.invoke(any()) }
    }
}