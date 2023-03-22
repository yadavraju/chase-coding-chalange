package com.example.weather.utils.factory

import com.google.android.gms.maps.model.LatLng

object ModelDefault {
    fun latLng() = LatLng(0.0, 0.0)
}

object MockResponse {
    object GetCurrentWeather {
        const val STATUS_200 = "mock-responses/get-weather-by-city.json"
    }
}
