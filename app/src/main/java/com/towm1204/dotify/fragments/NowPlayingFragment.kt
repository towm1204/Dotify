package com.towm1204.dotify.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ericchee.songdataprovider.Song

import com.towm1204.dotify.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {
    private var currentSong: Song? = null
    private var plays: Int? = null

    companion object {
        const val NOW_PLAYING_ARG = "now_playing_arg"
        const val OUT_PLAYS = "out_plays"
        const val OUT_SONG = "out_song"
        val TAG: String = NowPlayingFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            arguments?.let {
                val curSong = it.getParcelable<Song>(NOW_PLAYING_ARG)
                if (curSong != null) {
                    this.currentSong = curSong
                }
            }

            plays = Random.nextInt(100, 1000)
        } else {
            with(savedInstanceState) {
                plays = getInt(OUT_PLAYS)
                currentSong = getParcelable(OUT_SONG)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(OUT_SONG, currentSong)
        outState.putInt(OUT_PLAYS, plays!!)
        super.onSaveInstanceState(outState)
    }

    private fun playClicked() {
        if (plays != null) {
            plays = plays!! + 1
        }
        updateSongView()
    }

    private fun prevClicked() {
        Toast.makeText(context, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    private fun nextClicked() {
        Toast.makeText(context, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateSongView()
    }

    fun updateSong(song:Song) {
        this.currentSong = song
        updateSongView()
    }

    private fun updateSongView() {
        currentSong?.let {
            ivAlbumArt.setImageResource(it.largeImageID)
            tvArtist.text = it.artist
            tvTitle.text = it.title
        }

        if (plays != null) {
            tvNoPlays.text = "$plays plays"
        }

        ibPlayButton.setOnClickListener {
            playClicked()
        }

        ibPrevSong.setOnClickListener {
            prevClicked()
        }

        ibNextSong.setOnClickListener{
            nextClicked()
        }



    }

}
