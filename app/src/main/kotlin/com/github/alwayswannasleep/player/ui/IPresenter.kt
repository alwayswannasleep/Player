package com.github.alwayswannasleep.player.ui

import android.os.Bundle

interface IPresenter {

    fun onSaveInstanceState(out: Bundle)

    fun onRestoreInstanceState(input: Bundle)

    fun onStart()

    fun onStop()
}