package com.example.pokemon.data.data.db

import com.example.pokemon.data.data.db.model.PokeResultRoomEntity
import com.example.pokemon.data.data.db.model.PokemonRoomEntity
import com.example.pokemon.data.data.db.model.SpritesRoomEntity
import com.example.pokemon.domain.model.PokeResult
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.Sprites
import javax.inject.Inject

class EntityMapper @Inject constructor() {


    fun pokeResultFromDomainModelToRoomModel(entity: List<PokeResultRoomEntity>): List<PokeResult> {
        return entity.map {
            PokeResult(
                name = it.name,
                url = it.url
            )
        }
    }

    fun pokeResultRoomEntityFromDomainModelToRoomModel(entity: List<PokeResult>): List<PokeResultRoomEntity> {
        return entity.map {
            PokeResultRoomEntity(
                name = it.name,
                url = it.url
            )
        }
    }


    fun fromDomainModelToRoomModel(entity: Pokemon): PokemonRoomEntity {
        return PokemonRoomEntity(
            id = entity.id,
            name = entity.name,
            height = entity.height,
            weight = entity.weight,
            sprites = SpritesRoomEntity(entity.sprites.frontDefault, entity.sprites.frontShiny)
        )
    }

    fun fromRoomModelToDomainModel(entity: PokemonRoomEntity): Pokemon {
        return Pokemon(
            id = entity.id,
            name = entity.name,
            height = entity.height,
            weight = entity.weight,
            sprites = Sprites(entity.sprites.frontDefault, entity.sprites.frontShiny)
        )
    }


}