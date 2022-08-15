package com.example.pokemon.data.networking

import com.example.pokemon.domain.PokemonInfoModel
import com.example.pokemon.domain.PokemonList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonApi {

    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(@Path("id") id: Int): Response<PokemonInfoModel>

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit
        : Int, @Query("offset") offset: Int
    ): Response<PokemonList>
}







