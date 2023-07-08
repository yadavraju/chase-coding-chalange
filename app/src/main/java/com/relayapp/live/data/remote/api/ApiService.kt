package com.relayapp.live.data.remote.api

import com.relayapp.live.data.model.authresponse.AuthResponse
import com.relayapp.live.data.model.authresponse.ReferralResponse
import com.relayapp.live.data.model.coinresponse.CoinResponse
import com.relayapp.live.data.model.liveresponse.RoomDataResponse
import com.relayapp.live.domain.repository.AuthRequest
import com.relayapp.live.domain.repository.ReferralRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/signupLogin")
    fun signSignup(
        @Body authRequest: AuthRequest
    ): Flow<AuthResponse>

    @POST("referral/addNewReferral")
    fun addReferral(
        @Body authRequest: ReferralRequest
    ): Flow<ReferralResponse>

    @GET("room/{roomType}/{pageNumber}")
    fun getRoomData(
        @Path("roomType") roomPath: String,
        @Path("pageNumber") pageNumber: Int
    ): Flow<RoomDataResponse>

    @GET("coin")
    fun getCoinData(): Flow<CoinResponse>
}