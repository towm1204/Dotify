package com.towm1204.dotify

import android.app.Application
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.towm1204.dotify.manager.MusicManager

class DotifyApp: Application() {
    lateinit var musicManager: MusicManager

    override fun onCreate() {
        super.onCreate()
        this.musicManager = MusicManager()

    }


}