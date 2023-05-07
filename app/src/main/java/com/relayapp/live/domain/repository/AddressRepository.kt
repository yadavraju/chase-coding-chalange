package com.relayapp.live.domain.repository

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    fun getLastCityName(): Flow<String>

    fun getCurrentLocation(): Flow<LatLng>
}
