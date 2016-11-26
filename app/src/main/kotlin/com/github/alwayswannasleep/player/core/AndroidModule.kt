package com.github.alwayswannasleep.player.core

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule constructor(private val application: PlayerApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext() = application

}