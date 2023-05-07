package com.relayapp.live.domain.usecase.weather

import com.relayapp.live.domain.repository.AddressRepository
import com.relayapp.live.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastCityUseCase @Inject constructor(
    private val addressRepository: AddressRepository
) : UseCase<Void, String>() {

    override fun execute(params: Void?): Flow<String> {
        return addressRepository.getLastCityName()
    }
}
