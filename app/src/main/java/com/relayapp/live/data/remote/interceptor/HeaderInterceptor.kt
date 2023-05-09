package com.relayapp.live.data.remote.interceptor

import com.relayapp.live.data.local.pref.AppPrefs
import com.relayapp.live.data.local.pref.AppPrefs.Companion.EMPTY_STRING
import com.relayapp.live.data.local.pref.PrefsHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(private val prefsHelper: PrefsHelper) : Interceptor {

    private var headers: MutableMap<String, String> = mutableMapOf()

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = prefsHelper.read(AppPrefs.API_KEY, "")
        var builder = chain.request().newBuilder()
        headers["Accept"] = "application/json"
        headers["Content-Type"] = "application/json"
        if (EMPTY_STRING != token) {
            headers["Authorization"] = "Bearer $token"
        }
        headers.forEach { entry ->
            builder = builder.addHeader(entry.key, entry.value)
        }
        return chain.proceed(builder.build())
    }
}
