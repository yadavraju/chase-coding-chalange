package com.example.weather.domain.usecase.weather

import com.example.weather.domain.repository.AddressRepository
import com.example.weather.domain.usecase.UseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentLocationUseCase @Inject constructor(
    private val addressRepository: AddressRepository,
) : UseCase<Unit, LatLng>() {
    override fun execute(params: Unit?): Flow<LatLng> = addressRepository.getCurrentLocation()
}
