package com.relayapp.live.core

import android.util.Log
import com.relayapp.live.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())
    }
}