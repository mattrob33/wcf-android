package com.mattrobertson.wcf.di

import com.mattrobertson.wcf.repo.ConfessionRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRepo() = ConfessionRepo()

}