package com.towm1204.dotify.manager

import android.util.Log
import com.towm1204.dotify.interfaces.DotifyService
import com.towm1204.dotify.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager(private val dotifyService: DotifyService) {

    fun getUserInfo(onUserReady: (User) -> Unit, onError: ((String) -> Unit)? = null) {
        dotifyService.getUser().enqueue(object:Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                if (user != null) {
                    onUserReady(user)
                } else {
                    onError?.invoke("${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onError?.invoke(t.message ?: "Unknown")
            }
        })

    }

}