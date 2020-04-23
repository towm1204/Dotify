package com.towm1204.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.towm1204.dotify.MainActivity.Companion.SONG_KEY
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {
    private lateinit var songAdapter: SongListAdapter
    private lateinit var currentSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"

        // song adapter
        val listOfSongs = SongDataProvider.getAllSongs().toMutableList()
        songAdapter  = SongListAdapter(listOfSongs)

        // set onclick listener for each song
        songAdapter.onSongClickListener = { someSong: Song ->
            currentSong = someSong
            tvMiniPlayerText.text = currentSong.title
        }
        rvSongList.adapter = songAdapter

        // shuffle button
        btnShuffle.setOnClickListener{
            val shuffledList = listOfSongs.apply {
                shuffle()
            }
            rvSongList.scrollToPosition(0)

            songAdapter.change(shuffledList)
        }

        // mini player launching music player
        clMiniPlayer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(SONG_KEY, currentSong)
            startActivity(intent)
        }




    }
}
