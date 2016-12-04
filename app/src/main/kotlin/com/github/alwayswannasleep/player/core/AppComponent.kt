package com.github.alwayswannasleep.player.core

import com.github.alwayswannasleep.player.ui.main.MainActivity
import com.github.alwayswannasleep.player.ui.main.MainActivityPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(presenter: MainActivityPresenter)
}