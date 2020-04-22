package com.towm1204.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {
    private lateinit var songAdapter: SongListAdapter
    private lateinit var currentSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"

        // song adapter
        songAdapter  = SongListAdapter(SongDataProvider.getAllSongs())

        // set onclick listener for each song
        songAdapter.onSongClickListener = { someSong: Song ->
            currentSong = someSong
            tvMiniPlayerText.text = currentSong.title
        }
        rvSongList.adapter = songAdapter

        // mini player
//        if (this::currentSong.isInitialized) {
//            tvMiniPlayerText.text = currentSong.title
//        }

        // shuffle button




    }
}
