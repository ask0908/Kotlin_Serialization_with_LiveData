package com.example.livedatatest.features.home.domain

import com.example.livedatatest.features.home.data.response.FeedResponse
import retrofit2.http.GET

interface HomeApi {
    @GET("home")
    @kotlin.jvm.Throws(Exception::class)
    suspend fun getHomeFeed(): FeedResponse
}