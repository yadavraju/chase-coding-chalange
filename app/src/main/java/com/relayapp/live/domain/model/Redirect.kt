package com.relayapp.live.domain.model
import com.relayapp.live.domain.annotation.Redirect

data class Redirect(@Redirect val redirect: Int, val redirectObject: Any? = null)
