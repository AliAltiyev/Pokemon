package com.example.pokemon.data.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemon.domain.PokeResult
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.PokemonApiResponse

@Database(
    entities = [PokemonApiResponse::class, PokeResult::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(PokemonApiTypeConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao


}