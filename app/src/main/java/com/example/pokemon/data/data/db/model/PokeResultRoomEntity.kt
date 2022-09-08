package com.example.pokemon.data.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PokeResultRoomEntity")
data class PokeResultRoomEntity(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "url")
    val url: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}