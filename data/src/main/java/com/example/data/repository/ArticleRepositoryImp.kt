package com.example.data.repository

import com.example.data.db.ArticlesDao
import com.example.data.remote.ApiServices
import com.example.domain.entity.Article
import com.example.domain.entity.ArticleResponse
import com.example.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow

class ArticleRepositoryImp(
    private val apiServices: ApiServices,
    private val articlesDao: ArticlesDao) : ArticleRepository {
    private val country: String = "us"
    private val apiKey: String = "8c911bed784e46de80b7fb6cd80ea232"
    override suspend fun getArticlesFromRemote(): ArticleResponse =
        apiServices.getArticles(country, apiKey)

    override  fun getArticlesFromLocal(): Flow<List<Article>> =
        articlesDao.getAllArticles()

    override suspend fun insertArticle(articles: List<Article>) =
        articlesDao.insertArticles(articles)

    override suspend  fun deleteArticles() =
        articlesDao.deleteAllArticles()


}