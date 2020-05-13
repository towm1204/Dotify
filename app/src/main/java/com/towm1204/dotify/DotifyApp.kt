package com.towm1204.dotify

import android.app.Application
import android.util.Log
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

class DotifyApp: Application() {
    lateinit var masterSongList: List<Song>

    override fun onCreate() {
        super.onCreate()
        masterSongList = SongDataProvider.getAllSongs()

    }

    fun updateSongList(newSongList: List<Song>) {
        masterSongList = newSongList
    }

    fun masterShuffle() {
        masterSongList = masterSongList.toMutableList().apply {
            shuffle()
        }.toList()
    }
}