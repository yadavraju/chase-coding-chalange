package com.relayapp.live.presentation.ui.dashboard

import com.relayapp.live.R

enum class BottomBarItem(
    val title: String,
    val icon: Int
) {
    LIVE(
        title = "Live",
        icon = R.drawable.ic_live,
    ),
    SEARCH(
        title = "Search",
        icon = R.drawable.ic_search,
    ),
    COINS(
        title = "Coins",
        icon = R.drawable.wallet_money,
    ),
    CHAT(
        title = "Chat",
        icon = R.drawable.message,
    ),
    PROFILE(
        title = "Profile",
        icon = R.drawable.ic_user_square,
    );

}