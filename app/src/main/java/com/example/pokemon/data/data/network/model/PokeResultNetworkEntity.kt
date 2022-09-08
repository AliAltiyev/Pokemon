package com.example.pokemon.data.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokeResultNetworkEntity(
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("url")
    val url: String
)

