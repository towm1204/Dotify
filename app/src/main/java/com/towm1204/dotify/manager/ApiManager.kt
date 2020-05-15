package com.towm1204.dotify.manager

import android.util.Log
import com.towm1204.dotify.interfaces.DotifyService
import com.towm1204.dotify.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager(private val dotifyService: DotifyService) {

    fun getUserInfo(onUserReady: (User) -> Unit, onError: (() -> Unit)? = null) {
        dotifyService.getUser().enqueue(object:Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                if (user != null) {
                    onUserReady(user)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("Toww", t.message)
                onError?.invoke()
            }
        })

    }

}