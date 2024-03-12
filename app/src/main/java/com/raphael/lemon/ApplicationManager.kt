package com.raphael.lemon

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationManager: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}