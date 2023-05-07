package com.relayapp.live.data.remote.response

import com.google.gson.annotations.SerializedName

data class ServerErrorResponse(
    @SerializedName("cod") val code: Int,
    @SerializedName("message") val message: String?
)
