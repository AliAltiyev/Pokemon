package com.example.pokemon.data.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PokemonRoomEntity")
data class PokemonRoomEntity(
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "weight")
    val weight: Int,
    @ColumnInfo(name = "height")
    val height: Int,
    @Embedded
    val sprites: SpritesRoomEntity
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
