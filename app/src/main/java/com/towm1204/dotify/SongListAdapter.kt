package com.towm1204.dotify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.towm1204.dotify.models.Song

class SongListAdapter(listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    private var listOfSongs = listOfSongs.toList()
    var onSongClickListener: ((song: Song) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListAdapter.SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        )
    }
    override fun getItemCount(): Int {
        return listOfSongs.size
    }

    override fun onBindViewHolder(holder: SongListAdapter.SongViewHolder, position: Int) {
        holder.bind(listOfSongs[position], position)
    }

    fun change(newSongList: List<Song>) {
        // no animations
//        listOfSongs = newSongList
//        notifyDataSetChanged()

        // with animations
        val callback = SongDiffCallback(listOfSongs, newSongList)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)

        // update list of song
        listOfSongs = newSongList.toList()
    }

    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.tvSongListTitle)
        private val artist = itemView.findViewById<TextView>(R.id.tvSongListArtist)
        private val albumArt = itemView.findViewById<ImageView>(R.id.ivSongListAlbumArt)

        fun bind(song: Song, position: Int) {
            title.text = song.title
            artist.text = song.artist
            Picasso.get().load(song.smallImageURL).into(albumArt)

            itemView.setOnClickListener {
                onSongClickListener?.invoke(song)
            }
        }
    }
}