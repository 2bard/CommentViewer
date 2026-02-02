package com.twobard.techtest.di.repository

import com.twobard.techtest.data.repository.CommentRepositoryImpl
import com.twobard.techtest.domain.repository.CommentRepository

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