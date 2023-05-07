package com.relayapp.live.data.model

import com.relayapp.live.data.base.DataModel
import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val long: Double
) : DataModel()
