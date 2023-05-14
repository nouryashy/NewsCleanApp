package com.example.domain.usecase

import com.example.domain.entity.Article
import com.example.domain.repository.ArticleRepository

class GetArticles(private val articleRepository: ArticleRepository) {
    suspend fun articlesFromRemote() = articleRepository.getArticlesFromRemote()
     fun articlesFromLocal() = articleRepository.getArticlesFromLocal()
    suspend fun deleteArticles() = articleRepository.deleteArticles()
    suspend fun insertArticle(articles: List<Article>) = articleRepository.insertArticle(articles)
}