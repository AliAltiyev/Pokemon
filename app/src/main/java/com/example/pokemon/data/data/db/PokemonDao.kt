package com.example.pokemon.data.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemon.domain.PokeResult
import com.example.pokemon.domain.PokemonApiResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemon(pokemon: List<PokeResult>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemonApiResponse(pokemon: List<PokemonApiResponse>)


    @Query("SELECT * FROM PokeResult")
    fun getPokemon(): Flow<List<PokeResult>>


    @Query("SELECT * FROM  PokemonApiResponse ")
    fun getPokemonApiResponse(): Flow<List<PokemonApiResponse>>
}