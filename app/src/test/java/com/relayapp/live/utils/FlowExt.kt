package com.relayapp.live.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> T.toFlow(): Flow<T> = flow {
    emit(this@toFlow)
}
