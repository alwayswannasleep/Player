package com.github.alwayswannasleep.player.ui.activity

import android.os.Bundle
import com.github.alwayswannasleep.player.core.PlayerApplication
import javax.inject.Inject

class MainActivity : GenericActivity() {

    @Inject lateinit var context: PlayerApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        projectApplication.appComponent.inject(this)

        context.toString()
    }
}
