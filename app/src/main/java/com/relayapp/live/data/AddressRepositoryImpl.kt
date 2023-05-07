package com.relayapp.live.data

import android.annotation.SuppressLint
import android.content.Context
import com.relayapp.live.R
import com.relayapp.live.data.local.pref.PrefsHelper
import com.relayapp.live.domain.asFlow
import com.relayapp.live.domain.exception.BaseException
import com.relayapp.live.domain.repository.AddressRepository
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AddressRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val prefsHelper: PrefsHelper
) : AddressRepository {

    override fun getLastCityName(): Flow<String> {
        return prefsHelper.getLastCity().asFlow()
    }

    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): Flow<LatLng> = flow {
        // Just want to get location only want.
        // If you want to emit the value every time `addOnSuccessListener()`
        // return, you could using `callBackFlow` instead `suspendCancelableCoroutine`.
        emit(suspendCancellableCoroutine { cancellableContinuation ->
            val fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)

            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationProviderClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token,
            ).addOnSuccessListener { location ->
                if (location != null) {
                    cancellableContinuation.resume(LatLng(location.latitude, location.longitude))
                } else {
                    val error = BaseException.SnackBarException(
                        message = context.getString(R.string.error_message_current_location_is_null)
                    )
                    cancellableContinuation.resumeWithException(error)
                }
            }.addOnFailureListener { exception ->
                val error = BaseException.SnackBarException(message = exception.message ?: "")
                cancellableContinuation.resumeWithException(error)
            }.addOnCompleteListener {
                cancellableContinuation.cancel()
            }.addOnCanceledListener {
                cancellableContinuation.cancel()
            }

            cancellableContinuation.invokeOnCancellation {
                cancellationTokenSource.cancel()
            }
        })
    }
}
