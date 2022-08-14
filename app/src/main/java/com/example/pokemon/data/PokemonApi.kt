package com.example.pokemon.data

import com.example.pokemon.domain.Pokemon
import io.reactivex.Single
import retrofit2.http.GET


interface PokemonApi {

    @GET("/api/v2/pokemon")
    fun getPokemon(): Single<ArrayList<Pokemon>>


}

