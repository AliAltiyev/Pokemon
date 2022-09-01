package com.example.pokemon.di

import android.content.Context
import androidx.room.Room
import com.example.pokemon.data.database.PokemonDao
import com.example.pokemon.data.database.PokemonDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

    private const val DATABASE_NAME = "pokemon.db"


    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): PokemonDataBase {
        return Room.databaseBuilder(context, PokemonDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideDao(pokemonDataBase: PokemonDataBase): PokemonDao = pokemonDataBase.pokemonDao()


}

