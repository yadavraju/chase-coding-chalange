package com.relayapp.live.presentation.auth

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.relayapp.live.data.model.authresponse.AuthResponse
import com.relayapp.live.data.model.authresponse.ReferralResponse
import com.relayapp.live.domain.exception.BaseException
import com.relayapp.live.domain.model.Response
import com.relayapp.live.domain.repository.AuthRepository
import com.relayapp.live.domain.repository.AuthRequest
import com.relayapp.live.domain.repository.OneTapSignInResponse
import com.relayapp.live.domain.usecase.auth.LoginSignUpUseCase
import com.relayapp.live.domain.usecase.auth.ReferralUseCase
import com.relayapp.live.presentation.base.BaseViewModel
import com.relayapp.live.presentation.base.ViewState
import com.relayapp.live.presentation.base.toBaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthViewSate(
    override val isLoading: Boolean = false,
    override val exception: BaseException? = null,
    val authResponse: AuthResponse? = null,
    val referralResponse: ReferralResponse? = null,
) : ViewState(isLoading, exception)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository,
    private val oneTapClient: SignInClient,
    private val authUseCase: LoginSignUpUseCase,
    private val referralUseCase: ReferralUseCase,
) : BaseViewModel() {

    private val _state = MutableStateFlow(AuthViewSate(isLoading = false))
    override val state: StateFlow<AuthViewSate>
        get() = _state

    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase

    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Response.Success(null))
        private set
//    var signInWithGoogleResponse by mutableStateOf<AuthRequest>(Response.Success(false))
//        private set

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResponse = Response.Loading
        oneTapSignInResponse = repo.oneTapSignInWithGoogle()
    }

    fun signInWithGoogle(result: Intent) = viewModelScope.launch {
        val credentials = oneTapClient.getSignInCredentialFromIntent(result)
        val googleIdToken = credentials.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        val signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredentials)
        doLoginAndSignupApiCall(signInWithGoogleResponse)
    }

    private fun doLoginAndSignupApiCall(request: AuthRequest?) = safeLunch {
        showLoading()
        Log.e("Raju", "doLoginAndSignupApiCall" + request)
        authUseCase.invoke(LoginSignUpUseCase.Params(request))
            .catch { throwable ->
                Log.e("Raju", "Signed in throwable" + throwable)
                _state.update {
                    it.copy(
                        isLoading = false,
                        exception = throwable.toBaseException()
                    )
                }
            }
            .collect { authResponse ->
                Log.e("Raju", "Signed in sucess" + authResponse)
                _state.update { it.copy(isLoading = false, authResponse = authResponse) }
            }
    }

    fun doReferralApiCall(code: String) = safeLunch {
        showLoading()
        referralUseCase.invoke(ReferralUseCase.Params(code))
            .catch { throwable ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        exception = throwable.toBaseException()
                    )
                }
            }
            .collect { response ->
                _state.update { it.copy(isLoading = false, referralResponse = response) }
            }
    }

    private fun showLoading() {
        _state.update { it.copy(isLoading = true) }
    }
}