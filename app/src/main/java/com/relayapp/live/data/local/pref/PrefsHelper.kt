package com.relayapp.live.data.local.pref

interface PrefsHelper {
    fun isFirstRun(): Boolean

    fun setFirstRun(enable: Boolean = false)

    fun saveLastCity(cityName: String)

    fun <T> read(key: String, defaultValue: T): T

    fun <T> write(key: String, value: T)

    fun getLastCity(): String

    fun clear(key: String): Unit

    fun clearEverything(callBack: () -> Unit = {})
}
