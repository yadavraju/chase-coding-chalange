package com.relayapp.live.data

import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.relayapp.live.data.local.pref.PrefsHelper
import com.relayapp.live.domain.model.Response
import com.relayapp.live.domain.repository.ProfileRepository
import com.relayapp.live.domain.repository.RevokeAccessResponse
import com.relayapp.live.domain.repository.SignOutResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    private var signInClient: GoogleSignInClient,
    private var prefsHelper: PrefsHelper
) : ProfileRepository {
    override val displayName = auth.currentUser?.displayName.toString()
    override val photoUrl = auth.currentUser?.photoUrl.toString()
    override val email = auth.currentUser?.email.toString()

    override suspend fun signOut(): SignOutResponse {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            prefsHelper.clearEverything()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun revokeAccess(): RevokeAccessResponse {
        return try {
            auth.currentUser?.apply {
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                auth.signOut()
                delete().await()
            }
            prefsHelper.clearEverything()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}