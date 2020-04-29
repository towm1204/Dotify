package com.towm1204.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.towm1204.dotify.fragments.SongListFragment

class OgMainActivity : AppCompatActivity(), OnSongClickListener {

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

    }

    override fun onSongClicked(song: Song) {
        TODO("Not yet implemented")
    }
}


