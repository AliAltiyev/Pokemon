package com.example.pokemon.domain.model

data class Pokemon(
    val id: Long,
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: Sprites
)
