package com.relayapp.live.data.remote.api

import com.relayapp.live.data.model.authresponse.AuthResponse
import com.relayapp.live.data.model.authresponse.ReferralResponse
import com.relayapp.live.domain.repository.AuthRequest
import com.relayapp.live.domain.repository.ReferralRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/signupLogin")
    fun signSignup(
        @Body authRequest: AuthRequest
    ): Flow<AuthResponse>

    @POST("referral/addNewReferral")
    fun addReferral(
        @Body authRequest: ReferralRequest
    ): Flow<ReferralResponse>
}