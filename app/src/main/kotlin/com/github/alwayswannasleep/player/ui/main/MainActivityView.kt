package com.github.alwayswannasleep.player.ui.main

import com.github.alwayswannasleep.player.model.Song
import com.github.alwayswannasleep.player.ui.IView

interface MainActivityView : IView {
    fun updateSongs(songs: List<Song>)
}