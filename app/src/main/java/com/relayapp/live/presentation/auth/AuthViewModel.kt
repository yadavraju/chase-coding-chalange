package com.relayapp.live.presentation.auth

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.relayapp.live.domain.model.Response
import com.relayapp.live.domain.repository.AuthRepository
import com.relayapp.live.domain.repository.OneTapSignInResponse
import com.relayapp.live.domain.repository.SignInWithGoogleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository,
    private val oneTapClient: SignInClient
) : ViewModel() {

    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase

    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Response.Success(null))
        private set
    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(Response.Success(false))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResponse = Response.Loading
        oneTapSignInResponse = repo.oneTapSignInWithGoogle()
    }

    fun signInWithGoogle(result: Intent) = viewModelScope.launch {
        oneTapSignInResponse = Response.Loading
        val credentials = oneTapClient.getSignInCredentialFromIntent(result)
        val googleIdToken = credentials.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredentials)
    }
}