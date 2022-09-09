package com.example.pokemon.data.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokemon.utils.PokemonApiTypeConvertor


@Entity(tableName = "PokemonApiResponseRoomEntity")
data class PokemonApiResponseRoomEntity(
    @ColumnInfo(name = "next")
    val next: String,
    @ColumnInfo(name = "previous")
    val previous: String,
    @ColumnInfo(name = "results")
    @TypeConverters(PokemonApiTypeConvertor::class)
    val results: List<PokeResultRoomEntity>
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Long = 0
}