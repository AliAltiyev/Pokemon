package com.example.pokemon.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class PokemonApiResponse(

    @Expose @SerializedName("count")
    val count: Int,
    @Expose @SerializedName("next") val next: String,
    @Expose @SerializedName("previous") val previous: String,
    @Expose @SerializedName("results")
    val results: List<PokeResult>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Entity
data class PokeResult(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Long = 0
}

