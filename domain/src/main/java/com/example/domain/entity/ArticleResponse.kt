package com.example.domain.entity

data class ArticleResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)