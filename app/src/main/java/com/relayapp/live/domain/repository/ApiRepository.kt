package com.relayapp.live.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.relayapp.live.data.model.authresponse.AuthResponse
import com.relayapp.live.data.model.authresponse.ReferralResponse
import com.relayapp.live.data.model.coinresponse.CoinResponse
import com.relayapp.live.data.model.liveresponse.RoomDataResponse
import com.relayapp.live.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias SignInWithGoogleResponse = Response<AuthRequest>

data class AuthRequest(
    val firstName: String,
    val lastName: String,
    val geoLocation: String = "USA",
    val email: String,
    val wasReferredBySomeone: Boolean = false,
    val wasReferredBySomeoneReferralCode: String = "",
    val loginType: String = "google"
)

data class ReferralRequest(
    val userId: String,
    val wasReferredBySomeone: Boolean = false,
    val wasReferredBySomeoneReferralCode: String = ""
)

interface ApiRepository {
    val isUserAuthenticatedInFirebase: Boolean

    val currentUser: FirebaseUser?

    val getUserID: String

    suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): AuthRequest?

    fun doSignInSignUpApiCall(request: AuthRequest): Flow<AuthResponse>

    fun referralApiCall(request: ReferralRequest): Flow<ReferralResponse>

    fun getRoomData(
        roomName: String,
        pageNumber: Int = 1
    ): Flow<RoomDataResponse>

    fun getCoinData(): Flow<CoinResponse>
}