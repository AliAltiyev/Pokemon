package com.example.pokemon.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit


class CustomSharedPreferences {


    private var sharedPreferences: SharedPreferences? = null

    @Volatile
    private var INSTANCE: CustomSharedPreferences? = null
    private val lock = Any()
    operator fun invoke(context: Context): CustomSharedPreferences =
        INSTANCE ?: synchronized(lock) {
            INSTANCE ?: makeCustomSharedPreferences(context).also {
                INSTANCE = it
            }
        }

    private fun makeCustomSharedPreferences(context: Context): CustomSharedPreferences {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return CustomSharedPreferences()
    }


    fun saveTime(time: Long) {
        sharedPreferences?.edit(commit = true) {
            putLong(SHARED_PREFERENCES_TIME_KEY, time)
        }

        }


    fun getTime() = sharedPreferences?.getLong(SHARED_PREFERENCES_TIME_KEY, 0) ?: 0

    companion object {
        const val SHARED_PREFERENCES_TIME_KEY = "time"
    }


}

