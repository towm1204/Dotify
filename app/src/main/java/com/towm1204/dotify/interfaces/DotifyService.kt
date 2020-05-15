package com.towm1204.dotify.interfaces

import com.towm1204.dotify.models.MusicLibrary
import com.towm1204.dotify.models.User
import retrofit2.Call
import retrofit2.http.GET

interface DotifyService {
    @GET("echeeUW/codesnippets/master/user_info.json")
    fun getUser(): Call<User>

    @GET("echeeUW/codesnippets/master/musiclibrary.json")
    fun getSongs(): Call<MusicLibrary>
}