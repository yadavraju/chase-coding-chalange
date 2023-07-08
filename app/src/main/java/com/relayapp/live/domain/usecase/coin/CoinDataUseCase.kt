package com.relayapp.live.domain.usecase.coin

import com.relayapp.live.data.model.coinresponse.CoinResponse
import com.relayapp.live.domain.repository.ApiRepository
import com.relayapp.live.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinDataUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) : UseCase<Unit, CoinResponse>() {

    override fun execute(params: Unit?): Flow<CoinResponse> {
        return apiRepository.getCoinData()
    }
}
