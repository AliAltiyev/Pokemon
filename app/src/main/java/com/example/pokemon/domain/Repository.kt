package com.example.pokemon.domain

import com.example.pokemon.data.data.db.model.PokeResultRoomEntity
import com.example.pokemon.data.data.db.model.PokemonRoomEntity
import com.example.pokemon.data.data.network.model.PokemonApiResponseNetworkEntity
import com.example.pokemon.data.data.network.model.PokemonNetworkEntity
import io.reactivex.Single

interface Repository {

    //Room
    suspend fun insertAllPokemon(list: List<PokeResultRoomEntity>)

    suspend fun insertPokemon(pokemon: PokemonRoomEntity)

    suspend fun getPokemonList(): List<PokeResultRoomEntity>

    suspend fun getPokemonById(id: Int): PokemonRoomEntity

    suspend fun searchPokemon(name: String): PokeResultRoomEntity


    //Network
    fun getPokemon(id: Int): Single<PokemonNetworkEntity>

    fun getData(): Single<PokemonApiResponseNetworkEntity>


}