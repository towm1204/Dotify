package com.towm1204.dotify.interfaces

import com.towm1204.dotify.models.Song

interface OnSongClickListener {
    fun onSongClicked(song: Song)
}