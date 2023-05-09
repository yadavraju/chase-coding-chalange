package com.relayapp.live.domain.usecase.auth

import android.util.Log
import com.relayapp.live.data.model.authresponse.AuthResponse
import com.relayapp.live.domain.asFlow
import com.relayapp.live.domain.exception.BaseException
import com.relayapp.live.domain.repository.AuthRepository
import com.relayapp.live.domain.repository.AuthRequest
import com.relayapp.live.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : UseCase<LoginSignUpUseCase.Params, AuthResponse>() {

    override fun execute(params: Params?): Flow<AuthResponse> {
        Log.e("Raju", "execute called" + params?.request)
        params?.request?.apply {
            return authRepository.doSignInSignUpApiCall(this)
        }
        return BaseException.AlertException(-1, "Gmail user data are null").asFlow()
    }

    data class Params(val request: AuthRequest?)

}
