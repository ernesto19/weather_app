package com.example.weather_app.data.preferences.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.weather_app.BuildConfig
import com.example.weather_app.utils.Constants.NAME_SHARE_PREFERENCES

class PreferencesManager(context: Context) {

    private val advancedKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences: SharedPreferences by lazy {
        if (BuildConfig.DEBUG) context.getSharedPreferences(
            NAME_SHARE_PREFERENCES, 0
        ) else EncryptedSharedPreferences.create(
            context,
            NAME_SHARE_PREFERENCES,
            advancedKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private val editor: SharedPreferences.Editor by lazy { sharedPreferences.edit() }

    fun setValue(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun setValue(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun setValue(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun setValue(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }

    fun setValue(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, String()) ?: String()
    }
}