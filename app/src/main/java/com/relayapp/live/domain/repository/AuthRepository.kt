package com.relayapp.live.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import com.relayapp.live.domain.model.Response

typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias SignInWithGoogleResponse = Response<Boolean>

interface AuthRepository {
    val isUserAuthenticatedInFirebase: Boolean

    suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse
}