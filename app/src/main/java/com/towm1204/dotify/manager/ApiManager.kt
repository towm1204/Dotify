package com.towm1204.dotify.manager

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class ApiManager(context: Context) {

    private var queue: RequestQueue = Volley.newRequestQueue(context)

    fun fetchUserInfo() {
        val retrofit = Retrofit.Builder()
            .baseUrl(" https://raw.githubusercontent.com/echeeUW/codesnippets/master/endpoint")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }

}