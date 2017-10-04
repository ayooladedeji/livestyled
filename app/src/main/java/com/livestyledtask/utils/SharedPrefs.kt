package com.livestyledtask.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Ayo on 04/10/2017.
 */

class SharedPrefs(context: Context) {

    private val PREFS_FILENAME = "com.livestyled"
    private val FAVOURITES = "favourites"

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var favourites: MutableSet<String>
        get() = prefs.getStringSet(FAVOURITES, mutableSetOf())
        set(value) {
            prefs.edit().clear().apply()
            prefs.edit().putStringSet(FAVOURITES, value).apply()
        }

}