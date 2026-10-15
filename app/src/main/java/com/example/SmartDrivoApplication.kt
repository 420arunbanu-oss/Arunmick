package com.example

import android.app.Application
import com.example.data.PreferencesManager

class SmartDrivoApplication : Application() {

    lateinit var preferencesManager: PreferencesManager
        private set

    override fun onCreate() {
        super.onCreate()
        preferencesManager = PreferencesManager(this)
        // All tamper check listeners and payment status checkers removed!
    }
}
