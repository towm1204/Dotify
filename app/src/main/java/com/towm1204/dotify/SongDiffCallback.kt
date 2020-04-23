package com.towm1204.dotify

import androidx.recyclerview.widget.DiffUtil
import com.ericchee.songdataprovider.Song

class SongDiffCallback(
    private val oldSongList: List<Song>, private val newSongList: List<Song>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldSongList.size

    override fun getNewListSize(): Int = newSongList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val oldSong = oldSongList[oldItemPosition]
        val newSong = newSongList[newItemPosition]

        return oldSong.id == newSong.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldSong = oldSongList[oldItemPosition]
        val newSong = newSongList[newItemPosition]
        return oldSong.title == newSong.title
    }
}