package com.towm1204.dotify.manager

import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

class MusicManager {
    var masterSongList: List<Song> = SongDataProvider.getAllSongs()
    var currentSong: Song? = null

    fun masterShuffle() {
        masterSongList = masterSongList.toMutableList().apply {
            shuffle()
        }.toList()
    }

    fun setCurSong(song: Song) {
        currentSong = song
    }

    fun getCurSong(): Song? {
        return currentSong
    }

}