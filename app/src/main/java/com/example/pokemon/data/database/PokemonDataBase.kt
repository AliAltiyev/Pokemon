package com.example.pokemon.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bumptech.glide.load.model.ByteArrayLoader
import com.example.pokemon.data.database.PokemonDataBase.Companion.DATABASE_VERSION
import com.example.pokemon.domain.PokemonInfoModel
import com.example.pokemon.domain.PokemonList
import com.example.pokemon.domain.Result
import com.example.pokemon.domain.Sprites
import com.example.pokemon.utils.ConvertorResultToString

@Database(
    entities = [ PokemonList::class,Result :: class],
    version = DATABASE_VERSION, exportSchema = false
)
@TypeConverters(ConvertorResultToString::class)
abstract class PokemonDataBase : RoomDatabase() {


    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDataBase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(lock) {
            INSTANCE ?: makeDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun makeDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PokemonDataBase::class.java,
                DATABASE_NAME
            ).build()

        private const val DATABASE_NAME = "pokemon.db"
        const val DATABASE_VERSION = 1
    }




}