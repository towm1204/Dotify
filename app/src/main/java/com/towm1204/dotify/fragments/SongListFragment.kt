package com.towm1204.dotify.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericchee.songdataprovider.Song
import com.towm1204.dotify.DotifyApp
import com.towm1204.dotify.interfaces.OnSongClickListener

import com.towm1204.dotify.R
import com.towm1204.dotify.SongListAdapter
import com.towm1204.dotify.manager.MusicManager
import kotlinx.android.synthetic.main.fragment_song_list.*


class SongListFragment : Fragment() {
    private lateinit var songAdapter: SongListAdapter
    private lateinit var musicManager: MusicManager
    private var onSongClickListener: OnSongClickListener? = null

    companion object {
        const val SONG_LIST_ARG = "song_list_arg"
        val TAG: String = SongListFragment::class.java.simpleName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // get ref to DotifyApp
        musicManager = (context.applicationContext as DotifyApp).musicManager

        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter  = SongListAdapter(musicManager.masterSongList)
        songAdapter.onSongClickListener = { someSong: Song ->
            onSongClickListener?.onSongClicked(someSong)
        }
        rvSongList.adapter = songAdapter

    }

    fun shuffleSongs() {
        rvSongList.scrollToPosition(0)
        musicManager.masterShuffle()
        songAdapter.change(musicManager.masterSongList)
    }
}
