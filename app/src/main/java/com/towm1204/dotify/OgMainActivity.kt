package com.towm1204.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.towm1204.dotify.fragments.NowPlayingFragment
import com.towm1204.dotify.fragments.SongListFragment
import com.towm1204.dotify.interfaces.OnSongClickListener
import com.towm1204.dotify.manager.ApiManager
import com.towm1204.dotify.manager.MusicManager
import com.towm1204.dotify.models.User
import kotlinx.android.synthetic.main.activity_og_main.*

class OgMainActivity : AppCompatActivity(),
    OnSongClickListener {
    private lateinit var musicManager: MusicManager
    private lateinit var apiManager: ApiManager
    private var songListFrag: SongListFragment? = null

    companion object {
        const val OUT_SONG = "out_song"
    }

    private fun getSongListFragment() = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as? SongListFragment

    private fun getNowPlayingFragment() = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_og_main)

        // initialize music Manager and api manager
        val dotifyApp = (application as DotifyApp)
        musicManager = dotifyApp.musicManager
        apiManager = dotifyApp.apiManager

        val songListFragment: SongListFragment? = getSongListFragment()
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
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(SongListFragment.TAG)
                .add(R.id.fragContainer, songListFrag!!, SongListFragment.TAG)
                .commit()
        } else {
            // for edge case of rotating on startup
            musicManager.getCurSong()?.let {
                tvMiniPlayerText.text = """${it.title} - ${it.artist}"""
            }
            // if we on the nowPlaying fragment when we recreate we don't show the miniplayer
            if (nowPlayingFragment == null) {
                clMiniPlayer.visibility = View.VISIBLE
            }
            // set songListFrag to the one retrieved from retrieved from last state
            songListFrag = songListFragment
        }

        // shuffle onclick listener
        btnShuffle.setOnClickListener {
            songListFrag?.shuffleSongs()
        }


        // mini player on click
        clMiniPlayer.setOnClickListener {
            // if current Song not null
            musicManager.getCurSong()?.let {
                val nowPlayingFrag = NowPlayingFragment()
                val nowPlayingArgs = Bundle().apply {
                    this.putParcelable(NowPlayingFragment.NOW_PLAYING_ARG, it)
                }
                nowPlayingFrag.arguments = nowPlayingArgs
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragContainer, nowPlayingFrag, NowPlayingFragment.TAG)
                    .addToBackStack(NowPlayingFragment.TAG)
                    .commit()

                clMiniPlayer.visibility = View.GONE
            }
        }

        // User info button on click
        btnUserInfo.setOnClickListener {
            apiManager.getUserInfo({ user ->
                val intent = Intent(this, UserInfoActivity::class.java)
                intent.putExtra("user_key", user)
                startActivity(intent)

            }, { t->
                Toast.makeText(this, "Error: $t", Toast.LENGTH_LONG).show()
            })
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

    override fun onSongClicked(song: Song) {
        musicManager.setCurSong(song)
        musicManager.getCurSong()?.let {
            tvMiniPlayerText.text = """${it.title} - ${it.artist}"""
        }
    }


}


