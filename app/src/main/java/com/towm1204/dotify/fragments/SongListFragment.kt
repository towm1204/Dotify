package com.towm1204.dotify.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericchee.songdataprovider.Song
import com.towm1204.dotify.DotifyApp
import com.towm1204.dotify.OnSongClickListener

import com.towm1204.dotify.R
import com.towm1204.dotify.SongListAdapter
import kotlinx.android.synthetic.main.fragment_song_list.*


class SongListFragment : Fragment() {
    private lateinit var songAdapter: SongListAdapter
    private var listOfSongs: MutableList<Song>? = null
    private var onSongClickListener: OnSongClickListener? = null

    companion object {
        const val SONG_LIST_ARG = "song_list_arg"
        val TAG: String = SongListFragment::class.java.simpleName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // get ref to DotifyApp
        val dotifyApp: DotifyApp = (context.applicationContext as DotifyApp)
        listOfSongs = dotifyApp.masterSongList.toMutableList()


        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(TAG, ArrayList(listOfSongs!!))
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter  = SongListAdapter(listOfSongs!!)
        songAdapter.onSongClickListener = { someSong: Song ->
            onSongClickListener?.onSongClicked(someSong)
        }
        rvSongList.adapter = songAdapter

    }

    fun shuffleSongs() {
        val shuffledList = listOfSongs?.shuffled()
        rvSongList.scrollToPosition(0)
        songAdapter.change(shuffledList!!)

        // change song in DotifyApp
        val dotifyApp = (context?.applicationContext as DotifyApp)
        dotifyApp.updateSongList(shuffledList)
    }
}
