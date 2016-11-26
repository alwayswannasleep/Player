package com.github.alwayswannasleep.player.storage

import android.content.ContentResolver
import android.content.Context
import android.provider.MediaStore

class MusicFilesManager constructor(context: Context) {

    private val MP3_SELECTION = "${MediaStore.Audio.Media.IS_MUSIC} != 0" +
            " AND ${MediaStore.Audio.Media.MIME_TYPE} = 'audio/mpeg'" +
            " AND ${MediaStore.Audio.Media.DURATION} >= 60000"

    private val PROJECTION = listOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME
    )

    private val SORT_ORDER = "${MediaStore.Audio.AudioColumns.TITLE} COLLATE LOCALIZED ASC"

    private val contentResolver: ContentResolver

    init {
        contentResolver = context.contentResolver
    }


}