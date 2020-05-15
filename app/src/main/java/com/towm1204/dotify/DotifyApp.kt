package com.towm1204.dotify

import android.app.Application
import com.towm1204.dotify.interfaces.DotifyService
import com.towm1204.dotify.manager.ApiManager
import com.towm1204.dotify.manager.MusicManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DotifyApp: Application() {
    private lateinit var dotifyService: DotifyService
    lateinit var musicManager: MusicManager
    lateinit var apiManager: ApiManager

    override fun onCreate() {
        super.onCreate()
        // music manager
        this.musicManager = MusicManager()


        // init retrofit in api manager
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        dotifyService = retrofit.create(DotifyService::class.java)
        this.apiManager = ApiManager(dotifyService)
    }
}