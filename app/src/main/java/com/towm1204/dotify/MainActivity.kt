package com.towm1204.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import kotlin.random.Random
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val SONG_KEY = "song_key"
    }

    private var plays: Int = Random.nextInt(100, 1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get current song from intent and set that song us
        val currentSong = intent.getParcelableExtra<Song>(SONG_KEY)
        if (currentSong != null) {
            ivAlbumArt.setImageResource(currentSong.largeImageID)
            tvArtist.text = currentSong.artist
            tvTitle.text = currentSong.title
        }

        // initialize no of plays
        tvNoPlays.text = "$plays plays"

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

    private fun playClicked() {
        plays++
        tvNoPlays.text = "$plays plays"
    }

    private fun prevClicked() {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    private fun nextClicked() {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

}
