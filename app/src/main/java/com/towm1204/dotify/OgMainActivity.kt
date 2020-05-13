package com.towm1204.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.towm1204.dotify.fragments.NowPlayingFragment
import com.towm1204.dotify.fragments.SongListFragment
import kotlinx.android.synthetic.main.activity_og_main.*

class OgMainActivity : AppCompatActivity(), OnSongClickListener {
    private var currentSong: Song? = null
    private var songListFrag: SongListFragment? = null

    companion object {
        const val OUT_SONG = "out_song"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_og_main)

        val songListFragment = getSongListFragment()
        val nowPlayingFragment: NowPlayingFragment? = getNowPlayingFragment()

        // make sure to check back stack to see if we want to put up button up
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // if there is no existing songListFragment create a new fragment
        // else we just just update our current song
        if (songListFragment == null) {
            clMiniPlayer.visibility = View.VISIBLE
            songListFrag = SongListFragment()
//            val songListArgs = Bundle().apply {
//                val arrayListOfSongs = ArrayList(SongDataProvider.getAllSongs())
//                this.putParcelableArrayList(SongListFragment.SONG_LIST_ARG, arrayListOfSongs)
//            }
//            songListFrag?.arguments = songListArgs
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(SongListFragment.TAG)
                .add(R.id.fragContainer, songListFrag!!, SongListFragment.TAG)
                .commit()
        } else {
            // get currentSong from savedInstanceState
            with(savedInstanceState) {
                currentSong = this?.getParcelable(OUT_SONG)
            }
            // for edge case of rotating on startup
            currentSong?.let {
                tvMiniPlayerText.text = it.title + " - " + it.artist
            }
            // if we on the nowPlaying fragment when we recreate we don't show the miniplayer
            if (nowPlayingFragment == null) {
                clMiniPlayer.visibility = View.VISIBLE
            }
            // set songListFrag to the one retrieved from savedInstanceState
            songListFrag = songListFragment
        }

        // shuffle onclick listener
        btnShuffle.setOnClickListener {
            songListFrag?.shuffleSongs()
        }


        // mini player on click
        clMiniPlayer.setOnClickListener {
            if (currentSong != null) {
                clMiniPlayer.visibility = View.GONE
                val nowPlayingFrag = NowPlayingFragment()
                val nowPlayingArgs = Bundle().apply {
                    this.putParcelable(NowPlayingFragment.NOW_PLAYING_ARG, currentSong)
                }
                nowPlayingFrag.arguments = nowPlayingArgs
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragContainer, nowPlayingFrag, NowPlayingFragment.TAG)
                    .addToBackStack(NowPlayingFragment.TAG)
                    .commit()
            }
        }

        // on back stack change
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }

    override fun onBackPressed() {
        // if there's more than 1 stack -> NowPlaying is Open we call popStack
        // else just finish the activity -> on SongList Fragment
        if (supportFragmentManager.backStackEntryCount > 1) {
            onSupportNavigateUp()
        } else {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        clMiniPlayer.visibility = View.VISIBLE
        return super.onSupportNavigateUp()
    }

    private fun getSongListFragment() = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as? SongListFragment

    private fun getNowPlayingFragment() = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment



    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(OUT_SONG, currentSong)
        super.onSaveInstanceState(outState)
    }

    override fun onSongClicked(song: Song) {
        currentSong = song
        tvMiniPlayerText.text = currentSong?.title + " - " + currentSong?.artist
    }


}


