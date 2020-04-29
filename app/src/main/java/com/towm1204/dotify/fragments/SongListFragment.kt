package com.towm1204.dotify.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericchee.songdataprovider.Song

import com.towm1204.dotify.R
import com.towm1204.dotify.SongListAdapter
import kotlinx.android.synthetic.main.activity_song_list.*


class SongListFragment : Fragment() {
    private lateinit var songAdapter: SongListAdapter
    private var listOfSongs: List<Song>? = null

    companion object {
        const val SONG_LIST_ARG = "song_list_arg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            this.listOfSongs = it.getParcelableArrayList<Song>(SONG_LIST_ARG)?.toMutableList()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter  = SongListAdapter(listOfSongs!!)
        rvSongList.adapter = songAdapter

    }
}
