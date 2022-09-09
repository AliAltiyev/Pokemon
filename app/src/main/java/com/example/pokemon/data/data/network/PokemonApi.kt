package com.example.pokemon.data.data.network

import com.example.pokemon.data.data.network.model.PokemonApiResponseNetworkEntity
import com.example.pokemon.data.data.network.model.PokemonNetworkEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonApi {

    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Single<PokemonNetworkEntity>

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit
        : Int, @Query("offset") offset: Int
    ): Single<PokemonApiResponseNetworkEntity>
}










