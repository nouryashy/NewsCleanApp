package com.example.data.remote

import com.example.domain.entity.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("top-headlines")
    suspend fun getArticles(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String): ArticleResponse
}