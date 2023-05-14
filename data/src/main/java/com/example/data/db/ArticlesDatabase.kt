package com.example.data.db

import android.content.Context
import androidx.room.*
import com.example.domain.entity.Article
import java.time.Instant

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)

abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticlesDao
}