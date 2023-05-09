package com.relayapp.live.data.model.authresponse

import com.google.gson.annotations.SerializedName

data class ReferralResponse(
    @SerializedName("message")
    val message: String
)