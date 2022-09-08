package com.example.pokemon.data.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PokemonApiResponseNetworkEntity(

    @Expose @SerializedName("count")
    val count: Int,
    @Expose @SerializedName("next") val next: String,

    @Expose @SerializedName("previous") val previous: String,

    @Expose @SerializedName("results")
    val results: List<PokeResultNetworkEntity>
)






