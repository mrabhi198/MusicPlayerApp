package com.example.musicplayerapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<List<MyData>?> {
            override fun onResponse(p0: Call<List<MyData>?>, response: Response<List<MyData>?>) {
                //if API call is success then this method is called
                val dataList = response.body()
                val textView = findViewById<TextView>(R.id.helloText)
                textView.text = dataList.toString()
                Log.d("TAG: onResponse", "onResponse: " + response.body())
            }

            override fun onFailure(p0: Call<List<MyData>?>, t: Throwable) {
                //if API call is fail then this method is called
                Log.d("TAG: onFailure", "onFailure: " + t.message)
            }
        })
    }
}