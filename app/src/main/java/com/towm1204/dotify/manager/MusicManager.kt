package com.towm1204.dotify.manager

import com.towm1204.dotify.models.Song
import com.towm1204.dotify.interfaces.SongChangeListener

class MusicManager {
    var masterSongList: List<Song> = listOf()
    var currentSong: Song? = null
    var songChangeListener: SongChangeListener? = null

    fun masterShuffle() {
        masterSongList = masterSongList.toMutableList().apply {
            shuffle()
        }.toList()
    }

    fun setCurSong(song: Song) {
        currentSong = song
    }

    fun nextTrack() {
        val index = masterSongList.indexOf(currentSong!!)
        if (index < masterSongList.lastIndex) {
            setCurSong(masterSongList[index + 1])
        } else {
            setCurSong(masterSongList[0])
        }
        // then call listener
        songChangeListener?.updateCurSong()
    }

    fun prevTrack() {
        val index = masterSongList.indexOf(currentSong!!)
        if (index > 0) {
            setCurSong(masterSongList[index -1])
        } else {
            setCurSong(masterSongList[masterSongList.lastIndex])
        }

        songChangeListener?.updateCurSong()


    }

    fun getCurSong(): Song? {
        return currentSong
    }

}