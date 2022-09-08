package com.example.pokemon.di

import android.content.Context
import androidx.room.Room
import com.example.pokemon.data.data.db.AppDatabase
import com.example.pokemon.data.data.db.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
private const val DATABASE_NAME = "pokemon_database"

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase): PokemonDao = appDatabase.pokemonDao()
}