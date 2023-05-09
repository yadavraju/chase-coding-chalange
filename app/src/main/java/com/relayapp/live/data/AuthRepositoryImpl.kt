package com.relayapp.live.data

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.relayapp.live.core.Constants.DISPLAY_NAME
import com.relayapp.live.core.Constants.EMAIL
import com.relayapp.live.core.Constants.PHOTO_URL
import com.relayapp.live.core.Constants.SIGN_IN_REQUEST
import com.relayapp.live.core.Constants.SIGN_UP_REQUEST
import com.relayapp.live.data.local.pref.AppPrefs.Companion.API_KEY
import com.relayapp.live.data.local.pref.AppPrefs.Companion.USER_ID
import com.relayapp.live.data.local.pref.PrefsHelper
import com.relayapp.live.data.model.authresponse.AuthResponse
import com.relayapp.live.data.model.authresponse.ReferralResponse
import com.relayapp.live.data.remote.api.AuthApi
import com.relayapp.live.domain.model.Response
import com.relayapp.live.domain.repository.AuthRepository
import com.relayapp.live.domain.repository.AuthRequest
import com.relayapp.live.domain.repository.OneTapSignInResponse
import com.relayapp.live.domain.repository.ReferralRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val authApi: AuthApi,
    private val prefsHelper: PrefsHelper
) : AuthRepository {

    override val currentUser = auth.currentUser

    override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Response.Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Response.Success(signUpResult)
            } catch (e: Exception) {
                Response.Failure(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): AuthRequest? {
        return try {
            val user = auth.signInWithCredential(googleCredential).await().user
            user?.run {
                val name = displayName?.split(" ")
                if (name != null && name.isNotEmpty() && email != null) {
                    return AuthRequest(
                        firstName = name[0],
                        lastName = name[1],
                        email = email!!,
                        wasReferredBySomeone = false,
                        wasReferredBySomeoneReferralCode = ""
                    )
                }
                return null
            }
        } catch (e: Exception) {
            return null
        }
    }

    override val getUserID: String
        get() = prefsHelper.read(USER_ID, "")


    override fun doSignInSignUpApiCall(request: AuthRequest): Flow<AuthResponse> {
        return authApi.signSignup(request).map { value ->
            prefsHelper.write(API_KEY, value.bearerToken)
            prefsHelper.write(USER_ID, value.data.id)
            value
        }
    }


    override fun referralApiCall(request: ReferralRequest): Flow<ReferralResponse> {
        return authApi.addReferral(request)
    }
}

fun FirebaseUser.toUser() = mapOf(
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString()
)