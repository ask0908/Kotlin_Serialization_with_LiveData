package com.example.livedatatest.views.base

import android.app.Application
import com.example.livedatatest.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(AppDebugTree())
        }
    }

    private class AppDebugTree: Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String =
            "${element.fileName}:${element.lineNumber}:${element.methodName}"
    }
}