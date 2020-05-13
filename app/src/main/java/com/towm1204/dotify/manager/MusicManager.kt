package com.towm1204.dotify.manager

import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

class MusicManager {
    var masterSongList: List<Song> = SongDataProvider.getAllSongs()

    fun masterShuffle() {
        masterSongList = masterSongList.toMutableList().apply {
            shuffle()
        }.toList()
    }

}