package com.example.pokemon.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemon.domain.Result

@Dao
interface PokemonDao {


//    @Insert
//    suspend fun insertAllPokemonList(vararg pokemonList: PokemonList): List<Long>


    @Insert
    suspend fun insertAllResult(vararg result: Result): List<Long>

//
//    @Query("SELECT * FROM pokemonList")
//    suspend fun getAllPokemonList(): List<PokemonList>


    @Query("SELECT * FROM pokemonResultList")
    suspend fun getAllPokemonResultList(): List<Result>

//
//    @Query("SELECT * FROM pokemonResultList WHERE uuid = :id")
//    suspend fun getResult(id: Int): Result
//
//    @Query("SELECT * FROM pokemonList WHERE uuid = :id")
//    suspend fun getPokemonFromPokemonList(id: Int): PokemonList


    @Delete
    suspend fun deletePokemonResult(result: Result)

    @Delete
    suspend fun deleteAllPokemonResult(list: List<Result>)
}


