package com.example.pokemon.data.data.network.model

data class PokemonNetworkEntity(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: SpritesNetworkEntity
)
