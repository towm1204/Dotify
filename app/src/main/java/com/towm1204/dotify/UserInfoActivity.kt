package com.towm1204.dotify

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.towm1204.dotify.models.User
import kotlinx.android.synthetic.main.activity_og_main.*
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {
    companion object {
        const val USER_KEY = "user_key"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val curUser = intent.getParcelableExtra<User>(USER_KEY)
        tvUserName.text = curUser?.username
        tvFirstLastName.text = "${curUser?.firstName} ${curUser?.lastName}"
        if (curUser.hasNose) {
            tvHasNose.text = "Has Nose"
        } else {
            tvHasNose.text = "Has No Nose"
        }
       // Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView)


    }
}
