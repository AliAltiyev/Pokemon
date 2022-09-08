package com.example.pokemon.utils

import com.example.pokemon.data.data.db.model.PokeResultRoomEntity
import com.example.pokemon.data.data.db.model.PokemonRoomEntity
import com.example.pokemon.data.data.db.model.SpritesRoomEntity
import com.example.pokemon.domain.model.PokeResult
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.Sprites

fun List<PokeResultRoomEntity>.pokeResultFromDomainModelToRoomModel(): List<PokeResult> =
    map {
        PokeResult(
            name = it.name,
            url = it.url
        )
    }

fun List<PokeResult>.pokeResultRoomEntityFromDomainModelToRoomModel(): List<PokeResultRoomEntity> =
    map {
        PokeResultRoomEntity(
            name = it.name,
            url = it.url
        )
    }

fun Pokemon.fromDomainModelToRoomModel(): PokemonRoomEntity =
    PokemonRoomEntity(
        id = id,
        name = name,
        height = height,
        weight = weight,
        sprites = SpritesRoomEntity(sprites.frontDefault, sprites.frontShiny)
    )

fun PokemonRoomEntity.fromRoomModelToDomainModel(): Pokemon =
    Pokemon(
        id = id,
        name = name,
        height = height,
        weight = weight,
        sprites = Sprites(sprites.frontDefault, sprites.frontShiny)
    )
