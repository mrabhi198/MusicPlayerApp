package com.example.musicplayerapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers(
        "X-RapidAPI-Key: 4619e6c416mshf680d7374e1c25fp19c86ejsn6192a592aa18",
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com"
    )
    @GET("search")
    fun getData(@Query("q") query: String ) : Call<List<MyData>>
}