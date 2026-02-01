package com.twobard.techtest.di.repository

import CommentRepository
import com.twobard.techtest.data.CommentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindItemRepository(
        impl: CommentRepositoryImpl
    ): CommentRepository
}