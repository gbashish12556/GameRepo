package com.example.android.architecture.blueprints.machinecodingporoject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GameApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}