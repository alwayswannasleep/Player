package com.github.alwayswannasleep.player.storage

import android.database.Cursor
import android.provider.MediaStore
import com.github.alwayswannasleep.player.model.Song
import java.util.*

fun Cursor.mapSongs(): List<Song> {
    if (!moveToFirst()) {
        close()
        return emptyList()
    }

    val idIndex = getColumnIndex(MediaStore.Audio.Media._ID)
    val titleIndex = getColumnIndex(MediaStore.Audio.Media.TITLE)
    val artistIndex = getColumnIndex(MediaStore.Audio.Media.ARTIST)
    val composerIndex = getColumnIndex(MediaStore.Audio.Media.COMPOSER)
    val albumIndex = getColumnIndex(MediaStore.Audio.Media.ALBUM)
    val dataIndex = getColumnIndex(MediaStore.Audio.Media.DATA)
    val displayNameIndex = getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)
    val durationIndex = getColumnIndex(MediaStore.Audio.Media.DURATION)
    val sizeIndex = getColumnIndex(MediaStore.Audio.Media.SIZE)
    val bookmarkIndex = getColumnIndex(MediaStore.Audio.Media.BOOKMARK)
    val dateAddedIndex = getColumnIndex(MediaStore.Audio.Media.DATE_ADDED)
    val yearIndex = getColumnIndex(MediaStore.Audio.Media.YEAR)

    val result = ArrayList<Song>()

    do {
        result.add(Song(
                getLong(idIndex),
                getString(titleIndex),
                getString(artistIndex),
                getString(composerIndex),
                getString(albumIndex),
                getString(dataIndex),
                getString(displayNameIndex),
                getLong(durationIndex),
                getLong(sizeIndex),
                getLong(bookmarkIndex),
                getLong(dateAddedIndex),
                getInt(yearIndex)
        ))
    } while (moveToNext())

    close()

    return result
}
