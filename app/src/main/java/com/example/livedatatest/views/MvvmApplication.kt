package com.example.livedatatest.views

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MvvmApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}