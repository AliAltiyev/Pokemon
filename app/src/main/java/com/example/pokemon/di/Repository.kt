package com.example.pokemon.di

import com.example.pokemon.data.data.RepositoryImpl
import com.example.pokemon.data.data.db.PokemonDao
import com.example.pokemon.data.data.network.PokemonApi
import com.example.pokemon.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
object Repository {

    @Singleton
    @Provides
    fun provideRepository(
        dao: PokemonDao,
        api: PokemonApi
    ): Repository {
        return RepositoryImpl(dao, api)
    }

}