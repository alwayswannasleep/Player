package com.github.alwayswannasleep.player.ui.main

import android.Manifest
import android.util.Log
import com.github.alwayswannasleep.player.storage.MusicFilesManager
import javax.inject.Inject

class MainActivityPresenter(val view: MainActivityView) : IMainActivityPresenter() {
    @Inject lateinit var musicFilesManager: MusicFilesManager

    init {
        view.getAppComponent().inject(this)
    }

    override fun onStart() {
        view.runWithPermissions(Manifest.permission.READ_EXTERNAL_STORAGE) {
            Log.i("Presenter", musicFilesManager.getSongs().toString())
        }
    }

    override fun onStop() {
    }
}