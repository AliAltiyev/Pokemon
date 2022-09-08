package com.example.pokemon.data.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "PokemonApiResponseRoomEntity")
data class PokemonApiResponseRoomEntity(
    @ColumnInfo(name = "count")
    val count: Int,
    @ColumnInfo(name = "next")
    val next: String,
    @ColumnInfo(name = "previous")
    val previous: String,
    @ColumnInfo(name = "results")
    val results: List<PokeResultRoomEntity>
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Long = 0
}