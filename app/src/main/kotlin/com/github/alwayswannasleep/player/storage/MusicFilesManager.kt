package com.github.alwayswannasleep.player.storage

import android.content.ContentResolver
import android.content.Context
import android.provider.MediaStore
import java.util.*

class MusicFilesManager constructor(context: Context) {

    private val MP3_SELECTION = "${MediaStore.Audio.Media.IS_MUSIC} != 0" +
            " AND ${MediaStore.Audio.Media.MIME_TYPE} = 'audio/mpeg'" +
            " AND ${MediaStore.Audio.Media.DURATION} >= 60000"

    private val PROJECTION = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.YEAR
    )

    private val SORT_ORDER = "${MediaStore.Audio.AudioColumns.TITLE} COLLATE LOCALIZED ASC"

    private val contentResolver: ContentResolver

    init {
        contentResolver = context.contentResolver
    }

    fun getSongs(): List<String> {
        val result = ArrayList<String>()

        val query = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                PROJECTION,
                MP3_SELECTION,
                null,
                SORT_ORDER
        )

        if (!query.moveToFirst()) {
            query.close()
            return result
        }

        do {
            result.add(
                    StringBuilder()
                            .append(query.getString(0))
                            .append(query.getString(1))
                            .append(query.getString(2))
                            .append(query.getString(3))
                            .append(query.getString(4))
                            .append(query.getString(5))
                            .toString()
            )
        } while (query.moveToNext())

        query.close()

        return result
    }
}