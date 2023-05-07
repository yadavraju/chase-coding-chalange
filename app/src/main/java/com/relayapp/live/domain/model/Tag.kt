package com.relayapp.live.domain.model

import com.relayapp.live.domain.annotation.TagName

data class Tag(@TagName val name: String, val message: String?)
