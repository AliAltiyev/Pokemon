package com.example.pokemon.domain.model

import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(

    val id: Long,
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: Sprites
)
