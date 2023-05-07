package com.relayapp.live.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class SuspendUseCase<in Params, out T> {

    protected abstract suspend fun execute(params: Params): T

    suspend operator fun invoke(params: Params): T = withContext(Dispatchers.IO) {
        execute(params)
    }
}
