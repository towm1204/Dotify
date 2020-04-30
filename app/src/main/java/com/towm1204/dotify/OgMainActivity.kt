package com.towm1204.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.towm1204.dotify.fragments.NowPlayingFragment
import com.towm1204.dotify.fragments.SongListFragment
import kotlinx.android.synthetic.main.activity_og_main.*
import kotlinx.android.synthetic.main.fragment_song_list.*

class OgMainActivity : AppCompatActivity(), OnSongClickListener {
    private var currentSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_og_main)

        val songListFrag = SongListFragment()
        val songListArgs = Bundle().apply {
            val arrayListOfSongs = ArrayList(SongDataProvider.getAllSongs())
            this.putParcelableArrayList(SongListFragment.SONG_LIST_ARG, arrayListOfSongs)
        }
        songListFrag.arguments = songListArgs
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, songListFrag)
            .commit()

        btnShuffle.setOnClickListener{
            songListFrag.shuffleSongs()
        }

        clMiniPlayer.setOnClickListener{
            if (currentSong != null) {
                clMiniPlayer.visibility = View.GONE
                val nowPlayingFrag = NowPlayingFragment()
                val nowPlayingArgs = Bundle().apply {
                    this.putParcelable(NowPlayingFragment.NOW_PLAYING_ARG, currentSong)
                }
                nowPlayingFrag.arguments = nowPlayingArgs
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragContainer, nowPlayingFrag)
                    .commit()
            }
        }

    }

    override fun onSongClicked(song: Song) {
        currentSong = song
        tvMiniPlayerText.text = currentSong?.title
    }


}


