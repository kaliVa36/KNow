package com.android.know

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KNowApplication: Application() {
    private lateinit var preferences: SharedPreferences

    companion object {
        private const val KNOW_PREFERENCES = "KNowPreferences"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@KNowApplication)
            modules(appModule, dataBaseModule, newsViewModel)
        }
        preferences = this.applicationContext.getSharedPreferences(KNOW_PREFERENCES, Context.MODE_PRIVATE)
    }
}
