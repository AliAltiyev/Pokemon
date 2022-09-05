package com.example.pokemon.data.data.network

import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.PokemonApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonApi {

    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(@Path("id") id: Int): Response<Pokemon>

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit
        : Int, @Query("offset") offset: Int
    ): Response<PokemonApiResponse>
}










