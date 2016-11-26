package com.github.alwayswannasleep.player.core

import com.github.alwayswannasleep.player.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface AppComponent {
    fun inject(activity: MainActivity)
}