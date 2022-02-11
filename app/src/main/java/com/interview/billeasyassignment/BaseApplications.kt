package com.interview.billeasyassignment

import android.app.Application
import timber.log.Timber

class BaseApplications: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeTimber()
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}