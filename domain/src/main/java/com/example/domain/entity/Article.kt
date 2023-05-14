package com.example.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "news_articles")
data class Article(
    @PrimaryKey val url: String,
    val id: Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val urlToImage: String
)