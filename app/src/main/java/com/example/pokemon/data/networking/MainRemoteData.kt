package com.example.pokemon.data.networking

import com.example.pokemon.data.networking.PokemonApi
import com.example.pokemon.domain.PokemonInfoModel
import com.example.pokemon.domain.PokemonList
import retrofit2.Response
import javax.inject.Inject

class MainRemoteData @Inject constructor(private val api: PokemonApi) {

    suspend fun getData(): Response<PokemonList> = api.getPokemonList(
        POKEMON_LIMIT_SIZE,
        POKEMON_OFFSET_SIZE
    )

    suspend fun getPokemon(id: Int): Response<PokemonInfoModel> = api.getPokemonInfo(id)


    companion object {
        private const val POKEMON_LIMIT_SIZE = 100
        private const val POKEMON_OFFSET_SIZE = 0

    }
}


