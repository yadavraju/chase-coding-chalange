package com.relayapp.live.domain.usecase.auth

import com.relayapp.live.data.local.pref.AppPrefs.Companion.USER_ID
import com.relayapp.live.data.local.pref.PrefsHelper
import com.relayapp.live.data.model.authresponse.ReferralResponse
import com.relayapp.live.domain.asFlow
import com.relayapp.live.domain.exception.BaseException
import com.relayapp.live.domain.repository.AuthRepository
import com.relayapp.live.domain.repository.ReferralRequest
import com.relayapp.live.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReferralUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefsHelper: PrefsHelper
) : UseCase<ReferralUseCase.Params, ReferralResponse>() {

    override fun execute(params: Params?): Flow<ReferralResponse> {
        val userId = prefsHelper.read(USER_ID, "")
        if (params != null && userId.isNotEmpty()) {
            val request = ReferralRequest(
                userId,
                params.referralCode.isNotEmpty(),
                params.referralCode
            )
            return authRepository.referralApiCall(request)
        }

        return BaseException.AlertException(-1, "Current can be null").asFlow()
    }

    data class Params(val referralCode: String)
}
