package com.example.pokemon.data.data.db

import com.example.pokemon.domain.PokeResult
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.PokemonApiResponse
import javax.inject.Inject

class MainLocaleData @Inject constructor(private val pokemonDao: PokemonDao) {

     fun getAllPokemon() = pokemonDao.getPokemon()

     fun getAllPokemonApiResponse() = pokemonDao.getPokemonApiResponse()

    suspend fun insertAllPokemonApiResponse(pokemonApiResponse: List<PokemonApiResponse>) =
        pokemonDao.insertAllPokemonApiResponse(pokemonApiResponse)

    suspend fun insertAllPokemon(pokemon: List<PokeResult>) =
        pokemonDao.insertAllPokemon(pokemon)

}