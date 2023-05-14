package com.example.domain.repository

import com.example.domain.entity.Article
import com.example.domain.entity.ArticleResponse
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getArticlesFromRemote(): ArticleResponse
     fun getArticlesFromLocal(): Flow<List<Article>>
    suspend fun insertArticle(articles:List<Article>)
    suspend fun deleteArticles()
}