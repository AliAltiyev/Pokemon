package com.example.pokemon.domain.model


data class PokemonApiResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokeResult>
)






