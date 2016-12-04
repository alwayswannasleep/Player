package com.github.alwayswannasleep.player.core

import android.content.Context
import com.github.alwayswannasleep.player.storage.MusicFilesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule constructor(private val application: PlayerApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideMusicFilesManager(context: Context): MusicFilesManager = MusicFilesManager(context)

    @Provides
    @Singleton
    fun providesProjectApplication(): PlayerApplication = application
}