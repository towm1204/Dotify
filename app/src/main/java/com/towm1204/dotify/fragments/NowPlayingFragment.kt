package com.towm1204.dotify.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.towm1204.dotify.DotifyApp

import com.towm1204.dotify.R
import com.towm1204.dotify.manager.MusicManager
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {
    private lateinit var musicManager: MusicManager

    companion object {
        const val NOW_PLAYING_ARG = "now_playing_arg"
        val TAG: String = NowPlayingFragment::class.java.simpleName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        musicManager = (context.applicationContext as DotifyApp).musicManager
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

    private fun updateSongView() {
        musicManager.getCurSong()?.let {
            ivAlbumArt.setImageResource(it.largeImageID)
            tvArtist.text = it.artist
            tvTitle.text = it.title
        }

        ibPrevSong.setOnClickListener {
            prevClicked()
        }

        ibNextSong.setOnClickListener{
            nextClicked()
        }
    }


    private fun nextClicked() {
        musicManager.nextTrack()
        // update view
        updateSongView()
    }

    private fun prevClicked() {
        musicManager.prevTrack()
        updateSongView()
    }

}
