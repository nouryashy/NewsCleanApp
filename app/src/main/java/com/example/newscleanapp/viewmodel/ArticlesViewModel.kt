package com.example.newscleanapp.viewmodel

import androidx.lifecycle.*
import com.example.domain.entity.Article
import com.example.domain.entity.ArticleResponse
import com.example.domain.usecase.GetArticles
import com.example.newscleanapp.state.Resource
import com.example.newscleanapp.state.networkBoundResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticles
) :
    ViewModel() {


    var articles = getArticles().asLiveData()


    fun getArticles() = networkBoundResource(
        query = {
            getArticlesUseCase.articlesFromLocal()
        },
        fetch = {
            delay(2000)
            getArticlesUseCase.articlesFromRemote()
        },
        saveFetchResult = { a ->
            getArticlesUseCase.deleteArticles()
            getArticlesUseCase.insertArticle(a.articles)

        }
    )


}