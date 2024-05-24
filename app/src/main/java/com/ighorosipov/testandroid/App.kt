package com.ighorosipov.testandroid

import android.app.Application
import com.ighorosipov.testandroid.di.AppComponent
import com.ighorosipov.testandroid.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}