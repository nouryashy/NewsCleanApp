package com.example.newscleanapp.di

import com.example.domain.repository.ArticleRepository
import com.example.domain.usecase.GetArticles
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUseCase(articleRepository: ArticleRepository): GetArticles {
        return GetArticles(articleRepository)
    }
}