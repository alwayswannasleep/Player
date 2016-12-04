package com.github.alwayswannasleep.player.ui.main

import android.os.Bundle
import com.github.alwayswannasleep.player.R
import com.github.alwayswannasleep.player.core.AppComponent
import com.github.alwayswannasleep.player.core.PlayerApplication
import com.github.alwayswannasleep.player.model.Song
import com.github.alwayswannasleep.player.ui.GenericActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : GenericActivity(), MainActivityView {

    @Inject lateinit var context: PlayerApplication

    private lateinit var presenter: IMainActivityPresenter
    private lateinit var adapter: SongsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this)
        adapter = SongsAdapter(context)
        audioFilesList.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        presenter.onStart()
    }

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun getAppComponent() = projectApplication.appComponent

    override fun updateSongs(songs: List<Song>) {
        adapter.setData(songs)
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }
}
