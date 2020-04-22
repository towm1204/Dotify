package com.towm1204.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {
    private lateinit var songAdapter: SongListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        title = "All Songs"
        val songAdapter  = SongListAdapter(SongDataProvider.getAllSongs())

        rvSongList.adapter = songAdapter

    }
}
