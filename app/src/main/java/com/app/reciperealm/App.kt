package com.app.reciperealm

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.app.reciperealm.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }
}
