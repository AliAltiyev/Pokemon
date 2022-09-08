package com.example.pokemon.data.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpritesNetworkEntity(
    @Expose @SerializedName("front_default")
    val frontDefault: String?,
    @Expose @SerializedName("front_shiny")
    val frontShiny: String?
)
