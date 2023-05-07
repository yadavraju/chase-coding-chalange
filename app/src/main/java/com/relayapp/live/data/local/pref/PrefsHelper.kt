package com.relayapp.live.data.local.pref

interface PrefsHelper {
    fun isFirstRun(): Boolean

    fun setFirstRun(enable: Boolean = false)

    fun saveLastCity(cityName: String)

    fun getLastCity(): String
}
