package com.github.alwayswannasleep.player.model

data class Song(
        var id: Long,
        var title: String?,
        var artist: String?,
        var composer: String?,
        var album: String?,
        var relativePath: String?,
        var displayName: String?,
        var durationMillis: Long,
        var fileSize: Long,
        var bookmark: Long,
        var addedTimestamp: Long,
        var songCreationYear: Int
)