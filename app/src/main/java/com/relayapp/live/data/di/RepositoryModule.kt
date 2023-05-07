package com.relayapp.live.data.di

import com.relayapp.live.data.AddressRepositoryImpl
import com.relayapp.live.data.WeatherRepositoryImpl
import com.relayapp.live.data.local.pref.AppPrefs
import com.relayapp.live.data.local.pref.PrefsHelper
import com.relayapp.live.domain.repository.AddressRepository
import com.relayapp.live.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providePrefHelper(appPrefs: AppPrefs): PrefsHelper {
        return appPrefs
    }

    @Provides
    @Singleton
    fun providerWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository =
        weatherRepositoryImpl

    @Provides
    @Singleton
    fun providerAddressRepository(addressRepositoryImpl: AddressRepositoryImpl): AddressRepository =
        addressRepositoryImpl
}
