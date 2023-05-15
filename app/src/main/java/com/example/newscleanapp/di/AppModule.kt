package com.example.newscleanapp.di

import android.app.Application
import androidx.room.Room
import com.example.data.db.ArticlesDao
import com.example.data.db.ArticlesDatabase
import com.example.data.remote.ApiServices
import com.example.newscleanapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }


    @Provides
    @Singleton
    fun provideDatabase(app: Application): ArticlesDatabase {
        return Room.databaseBuilder(app, ArticlesDatabase::class.java, "article_database")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(database: ArticlesDatabase): ArticlesDao {
        return database.articleDao()
    }
}