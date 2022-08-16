package com.example.pokemon.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.pokemon.utils.ConvertorResultToString
import com.google.gson.annotations.SerializedName


@Entity(tableName = "pokemonList")
data class PokemonList(
    @ColumnInfo(name = "count")
    @SerializedName("count")
    val count: Int?,

    @ColumnInfo(name = "next")
    @SerializedName("next")
    val next: String?,


    @SerializedName("previous")
    val previous: String?,


    @ColumnInfo(name = "results")
    @SerializedName("results")
    val results: List<Result>?,

    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
)

@Entity(tableName = "pokemonResultList")
data class Result(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String?,

    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

    )
