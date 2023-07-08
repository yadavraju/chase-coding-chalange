package com.relayapp.live.data.model.coinresponse


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CoinResponse(
    @SerializedName("data")
    val data: List<CoinData>,
    @SerializedName("success")
    val success: Boolean
) : Parcelable

@Parcelize
data class CoinData(
    @SerializedName("coins")
    val coins: Int,
    @SerializedName("price")
    val price: Double
) : Parcelable