package com.relayapp.live.data.model.authresponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthResponse(
    @SerializedName("authtoken")
    val authtoken: String,
    @SerializedName("bearerToken")
    val bearerToken: String,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("newuser")
    val newuser: Boolean,
    @SerializedName("success")
    val success: Boolean
) : Parcelable

@Parcelize
data class Data(
    @SerializedName("accountDisabled")
    val accountDisabled: Boolean,
    @SerializedName("agoraUid")
    val agoraUid: Int,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("burnedCoins")
    val burnedCoins: Int,
    @SerializedName("cashableCoins")
    val cashableCoins: Int,
    @SerializedName("coins")
    val coins: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("currentRoom")
    val currentRoom: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("followers")
    val followers: List<String>,
    @SerializedName("followersCount")
    val followersCount: Int,
    @SerializedName("following")
    val following: List<String>,
    @SerializedName("followingCount")
    val followingCount: Int,
    @SerializedName("fwId")
    val fwId: String,
    @SerializedName("fwSubAccount")
    val fwSubAccount: String,
    @SerializedName("geoLocation")
    val geoLocation: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("interests")
    val interests: List<String>,
    @SerializedName("isUserAccountDormant")
    val isUserAccountDormant: Boolean,
    @SerializedName("isUserAccountPremium")
    val isUserAccountPremium: Boolean,
    @SerializedName("joinedClubs")
    val joinedClubs: List<String>,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("loginType")
    val loginType: String,
    @SerializedName("mpesaNumber")
    val mpesaNumber: String,
    @SerializedName("muted")
    val muted: Boolean,
    @SerializedName("notificationToken")
    val notificationToken: String,
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    @SerializedName("pendingWallet")
    val pendingWallet: Int,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("premiumEndDate")
    val premiumEndDate: String?,
    @SerializedName("profilePhoto")
    val profilePhoto: String,
    @SerializedName("referralCode")
    val referralCode: String,
    @SerializedName("renewUpgrade")
    val renewUpgrade: Boolean,
    @SerializedName("role")
    val role: String,
    @SerializedName("roomUid")
    val roomUid: String,
    @SerializedName("shopId")
    val shopId: String?,
    @SerializedName("socialMedia")
    val socialMedia: List<String>,
    @SerializedName("stageName")
    val stageName: String,
    @SerializedName("stripeAccountId")
    val stripeAccountId: String,
    @SerializedName("stripeBankAccount")
    val stripeBankAccount: String,
    @SerializedName("totalLifetimeCoins")
    val totalLifetimeCoins: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("userLastActivity")
    val userLastActivity: String,
    @SerializedName("userRanking")
    val userRanking: Double,
    @SerializedName("__v")
    val v: Int,
    @SerializedName("wallet")
    val wallet: Int,
    @SerializedName("wasReferredBySomeone")
    val wasReferredBySomeone: Boolean,
    @SerializedName("wasReferredBySomeoneReferralCode")
    val wasReferredBySomeoneReferralCode: String
) : Parcelable