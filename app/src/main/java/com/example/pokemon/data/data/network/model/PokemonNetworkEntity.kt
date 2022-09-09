package com.example.pokemon.data.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonNetworkEntity(
    @Expose @SerializedName("id")
    val id: Long,
    @Expose @SerializedName("name")
    val name: String,
    @Expose @SerializedName("weight")
    val weight: Int,
    @Expose @SerializedName("height")
    val height: Int,
    @Expose @SerializedName("sprites")
    val sprites: SpritesNetworkEntity
)