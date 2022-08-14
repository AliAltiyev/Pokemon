package com.example.pokemon.data

import com.example.pokemon.domain.CatFactItem
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.Result
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET


interface PokemonApi {

    @GET("/api/v2/pokemon")
   suspend fun getPokemon(): Response<Pokemon>





}

