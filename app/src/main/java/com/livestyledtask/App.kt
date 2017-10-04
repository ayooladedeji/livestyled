package com.livestyledtask

import android.app.Application
import com.livestyledtask.utils.SharedPrefs

/**
 * Created by Ayo on 04/10/2017.
 */

class App: Application(){

    companion object {
        lateinit var sharedPrefs: SharedPrefs
    }

    override fun onCreate() {
        super.onCreate()
        sharedPrefs = SharedPrefs(this)
    }
}