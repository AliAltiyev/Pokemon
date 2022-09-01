package com.example.pokemon.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemon.domain.Result

@Dao
interface PokemonDao {

    @Insert
    suspend fun insertAllResult(vararg result: Result): List<Long>

    @Query("SELECT * FROM pokemonResultList")
    suspend fun getAllPokemonResultList(): List<Result>


    @Delete
    suspend fun deletePokemonResult(result: Result)

    @Delete
    suspend fun deleteAllPokemonResult(list: List<Result>)
}


