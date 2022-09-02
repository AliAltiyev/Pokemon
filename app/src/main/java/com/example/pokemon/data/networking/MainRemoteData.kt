package com.example.pokemon.data.networking

import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.PokemonApiResponse
import retrofit2.Response
import javax.inject.Inject

class MainRemoteData @Inject constructor(private val api: PokemonApi) {

    suspend fun getData(): Response<PokemonApiResponse> = api.getPokemonList(
        POKEMON_LIMIT_SIZE,
        POKEMON_OFFSET_SIZE
    )

    suspend fun getPokemon(id: Int): Response<Pokemon> = api.getPokemonInfo(id)


    companion object {
        private const val POKEMON_LIMIT_SIZE = 50
        private const val POKEMON_OFFSET_SIZE = 0

    }
}


