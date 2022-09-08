package com.example.pokemon.data.data.network

import com.example.pokemon.domain.model.PokeResult
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonApi {

    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Single<Pokemon>

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit
        : Int, @Query("offset") offset: Int
    ): Single<PokemonApiResponse>
}










