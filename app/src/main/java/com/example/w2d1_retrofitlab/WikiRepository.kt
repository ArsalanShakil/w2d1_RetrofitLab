package com.example.w2d1_retrofitlab

class WikiRepository {
    private val call = ServiceApi.service
    suspend fun hitCountCheck(name: String) = call.userName(name)
}