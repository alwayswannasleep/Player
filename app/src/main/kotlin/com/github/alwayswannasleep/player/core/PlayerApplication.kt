package com.github.alwayswannasleep.player.core

import android.app.Application

class PlayerApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
    }
}