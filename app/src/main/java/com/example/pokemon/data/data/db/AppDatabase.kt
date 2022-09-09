package com.example.pokemon.data.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemon.data.data.db.model.PokeResultRoomEntity
import com.example.pokemon.data.data.db.model.PokemonApiResponseRoomEntity
import com.example.pokemon.data.data.db.model.PokemonRoomEntity
import com.example.pokemon.data.data.db.model.SpritesRoomEntity
import com.example.pokemon.utils.PokemonApiTypeConvertor
private const val DATABASE_VERSION = 5
@Database(
    entities = [PokemonRoomEntity::class,
        PokemonApiResponseRoomEntity::class,
        PokeResultRoomEntity::class,
        SpritesRoomEntity::class],
    version = DATABASE_VERSION,
)
@TypeConverters(PokemonApiTypeConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao


}