package com.towm1204.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlin.random.Random
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var plays: Int = Random.nextInt(100, 1000)
    private var changingUser: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        btnChangeUser.setOnClickListener{
            changeUser()
        }
    }

    private fun changeUser() {
        if (!changingUser) {
            btnChangeUser.text = "Apply"
            tvUsername.visibility = View.INVISIBLE
            etNewUsername.visibility = View.VISIBLE
            changingUser = true
        } else {
            if (etNewUsername.text.isNotEmpty()) {
                val newUsername = etNewUsername.text.toString()
                tvUsername.text = newUsername
                btnChangeUser.text = "Change User"
                tvUsername.visibility = View.VISIBLE
                etNewUsername.visibility = View.INVISIBLE
                changingUser = false
            }
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
