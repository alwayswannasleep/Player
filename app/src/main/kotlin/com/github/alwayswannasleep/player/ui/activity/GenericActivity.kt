package com.github.alwayswannasleep.player.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.alwayswannasleep.player.core.PlayerApplication

abstract class GenericActivity : AppCompatActivity() {

    protected lateinit var projectApplication: PlayerApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        projectApplication = application as PlayerApplication
    }
}