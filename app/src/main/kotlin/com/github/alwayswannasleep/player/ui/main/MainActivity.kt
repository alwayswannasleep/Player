package com.github.alwayswannasleep.player.ui.main

import android.os.Bundle
import com.github.alwayswannasleep.player.R
import com.github.alwayswannasleep.player.core.AppComponent
import com.github.alwayswannasleep.player.core.PlayerApplication
import com.github.alwayswannasleep.player.ui.GenericActivity
import javax.inject.Inject

class MainActivity : GenericActivity(), MainActivityView {

    @Inject lateinit var context: PlayerApplication

    private lateinit var presenter: IMainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainActivityPresenter(this)

        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        presenter.onStart()
    }

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun getAppComponent() = projectApplication.appComponent

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }
}
