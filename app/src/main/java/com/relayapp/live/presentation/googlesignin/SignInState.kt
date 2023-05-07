package com.relayapp.live.presentation.googlesignin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
