package com.github.alwayswannasleep.player.ui

import com.github.alwayswannasleep.player.core.AppComponent

interface IView {

    fun getAppComponent(): AppComponent

    fun runWithPermissions(vararg permissions: String, task: () -> Unit)
}