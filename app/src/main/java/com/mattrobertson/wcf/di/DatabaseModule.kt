package com.mattrobertson.wcf.di

import android.content.Context
import com.mattrobertson.wcf.data.db.ConfessionDatabase
import com.mattrobertson.wcf.data.db.dao.ChapterDao
import com.mattrobertson.wcf.repo.ConfessionRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ConfessionDatabase {
        return ConfessionDatabase.getInstance(context)
    }

    @Provides
    fun provideChapterDao(db: ConfessionDatabase): ChapterDao {
        return db.chapterDao()
    }

    @Provides
    fun provideConfessionRepo(chapterDao: ChapterDao) = ConfessionRepo(chapterDao)
}
