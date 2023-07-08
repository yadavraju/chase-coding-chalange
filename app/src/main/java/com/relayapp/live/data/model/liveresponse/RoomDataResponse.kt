package com.relayapp.live.data.model.liveresponse

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class RoomDataResponse(
    @SerializedName("currentPage")
    val currentPage: Int,
    @SerializedName("results")
    val results: List<RoomResult>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("totalCount")
    val totalCount: Int,
    @SerializedName("totalPages")
    val totalPages: Int
) : Parcelable


@Parcelize
data class RoomResult(
    @SerializedName("coverCharge")
    val coverCharge: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("previewCharge")
    val previewCharge: Int,
    @SerializedName("roomType")
    val roomType: String,
    @SerializedName("streamEnded")
    val streamEnded: Boolean,
    @SerializedName("streamEndedTime")
    val streamEndedTime: String?,
    @SerializedName("streamerId")
    val streamerId: StreamerId,
    @SerializedName("totalCoins")
    val totalCoins: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("__v")
    val v: Int,
    @SerializedName("viewers")
    val viewers: List<String>,
    @SerializedName("viewersKickedOut")
    val viewersKickedOut: List<String>,
    @SerializedName("viewersPreviewedPremiumRoom")
    val viewersPreviewedPremiumRoom: List<String>
) : Parcelable

@Parcelize
data class StreamerId(
    @SerializedName("_id")
    val id: String,
    @SerializedName("profilePicture")
    val profilePicture: String,
    @SerializedName("stageName")
    val stageName: String
) : Parcelable