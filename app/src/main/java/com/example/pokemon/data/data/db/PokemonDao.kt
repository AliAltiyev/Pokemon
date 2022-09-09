package com.example.pokemon.data.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemon.data.data.db.model.PokeResultRoomEntity
import com.example.pokemon.data.data.db.model.PokemonRoomEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemon(list: List<PokeResultRoomEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonRoomEntity)


    @Query("SELECT * FROM pokeresultroomentity")
    fun getPokemon(): List<PokeResultRoomEntity>


    @Query("SELECT * FROM pokemonroomentity WHERE id = :id")
    fun getPokemonById(id: Int): PokemonRoomEntity
}