package com.example.pokemon.data.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SpritesRoomEntity")
data class SpritesRoomEntity(
    val frontDefault: String?,
    val frontShiny: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Long = 0
}
