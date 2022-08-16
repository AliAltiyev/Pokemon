package com.example.pokemon.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PokemonInfoModel(

    @Expose
    @SerializedName("id") val id: Int?,


    @Expose
    @SerializedName("name") val name: String,

    @Expose
    @SerializedName("height") val height: Int,

    @Expose
    @SerializedName("weight") val weight: Int,

    @Expose
    @SerializedName("sprites") val sprites: Sprites,


    ) :Serializable

data
class Sprites(


    @Expose
    @SerializedName("front_default")
    val frontDefault: String?,


    @Expose
    @SerializedName("front_shiny") val frontShiny: String?,


    ) :Serializable


