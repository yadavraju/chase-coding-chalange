package com.relayapp.live.data.local.pref

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AppPrefs @Inject constructor(
    @ApplicationContext private val context: Context
) : PrefsHelper {

    private val prefs by lazy {
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    override fun isFirstRun(): Boolean {
        return prefs.getBoolean(FIRST_RUN_TAG, true)
    }

    override fun setFirstRun(enable: Boolean) {
        prefs.edit { putBoolean(FIRST_RUN_TAG, enable) }
    }

    override fun saveLastCity(cityName: String) {
        prefs.edit { putString(LAST_CITY_TAG, cityName) }
    }

    override fun getLastCity(): String {
        return prefs.getString(LAST_CITY_TAG, "") ?: ""
    }

    override fun <T> read(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> (prefs.getString(key, defaultValue as String) as T) ?: defaultValue
            is Int -> (prefs.getInt(key, defaultValue as Int) as T) ?: defaultValue
            is Boolean -> (prefs.getBoolean(key, defaultValue as Boolean) as T) ?: defaultValue
            is Long -> (prefs.getLong(key, defaultValue as Long) as T) ?: defaultValue
            else -> defaultValue
        }
    }

    override fun <T> write(key: String, value: T) {
        when (value) {
            is String -> prefs.edit { putString(key, value).apply() }
            is Int -> prefs.edit { putInt(key, value).apply() }
            is Boolean -> prefs.edit { putBoolean(key, value).apply() }
            is Long -> prefs.edit { putLong(key, value).apply() }
            else -> Unit
        }
    }

    override fun clear(key: String): Unit = prefs.edit {
        remove(key)
    }

    override fun clearEverything(callBack: () -> Unit) {
        prefs.edit {
            clear().commit()
            callBack.invoke()
        }
    }

    companion object {
        private const val FIRST_RUN_TAG = "first_run"
        private const val LAST_CITY_TAG = "country"
        const val API_KEY = "API_KEY"
        const val USER_ID = "USER_ID"
        const val EMPTY_STRING = ""
    }
}
