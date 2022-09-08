package com.example.pokemon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
object Repository {

    @Singleton
    @Provides
    fun provideRepository(): Repository {
        return Repository
    }

}