package com.relayapp.live.domain.repository

import com.relayapp.live.domain.model.Response

typealias SignOutResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>

interface ProfileRepository {
    val displayName: String
    val photoUrl: String
    val email: String

    suspend fun signOut(): SignOutResponse

    suspend fun revokeAccess(): RevokeAccessResponse
}