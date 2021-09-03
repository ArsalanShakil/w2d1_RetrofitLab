package com.example.w2d1_retrofitlab

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ServiceApi {
    const val URL = "https://en.wikipedia.org/w/"

    interface Service {
        @GET("api.php")
        suspend fun userName(@Query("action=query&format=json&list=search&srsearch=") action: String): GlobalModel.President
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(Service::class.java)!!
}
