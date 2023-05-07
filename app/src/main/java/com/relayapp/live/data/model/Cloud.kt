package com.relayapp.live.data.model

import com.relayapp.live.data.base.DataModel
import com.google.gson.annotations.SerializedName

data class Cloud(
    @SerializedName("all") val all: Int
) : DataModel()
