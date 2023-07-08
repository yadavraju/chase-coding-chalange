package com.relayapp.live.domain.usecase.live

import com.relayapp.live.data.local.pref.AppPrefs.Companion.USER_ID
import com.relayapp.live.data.local.pref.PrefsHelper
import com.relayapp.live.data.model.authresponse.ReferralResponse
import com.relayapp.live.data.model.liveresponse.RoomDataResponse
import com.relayapp.live.domain.asFlow
import com.relayapp.live.domain.exception.BaseException
import com.relayapp.live.domain.repository.ApiRepository
import com.relayapp.live.domain.repository.ReferralRequest
import com.relayapp.live.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomDataUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    private val prefsHelper: PrefsHelper
) : UseCase<RoomDataUseCase.Params, RoomDataResponse>() {

    override fun execute(params: Params?): Flow<RoomDataResponse> {
        val userId = prefsHelper.read(USER_ID, "")
        if (params != null && params.roomName.isNotEmpty()) {
            return apiRepository.getRoomData(params.roomName, params.pageNumber)
        }

        return BaseException.AlertException(-1, "Invalid Room").asFlow()
    }

    data class Params(val roomName: String, val pageNumber: Int)
}
