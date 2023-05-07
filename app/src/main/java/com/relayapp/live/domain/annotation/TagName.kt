package com.relayapp.live.domain.annotation

import androidx.annotation.StringDef
import com.relayapp.live.domain.annotation.TagName.Companion.PASSWORD_INCORRECT_TAG

@StringDef(PASSWORD_INCORRECT_TAG)
annotation class TagName {
    companion object {
        const val PASSWORD_INCORRECT_TAG = "password_incorrect_tag"
    }
}
