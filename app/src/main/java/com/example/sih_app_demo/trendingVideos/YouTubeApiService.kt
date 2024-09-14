package com.example.sih_app_demo.trendingVideos

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {
    @GET("search")
    fun getTrendingVideos(
        @Query("part") part: String,
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("maxResults") maxResults: Int,
        @Query("key") apiKey: String
    ): Call<YouTubeResponse>
}
