package com.example.pokemon.utils

import androidx.room.TypeConverter
import com.example.pokemon.data.data.db.model.PokeResultRoomEntity
import com.example.pokemon.domain.model.PokeResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


 object PokemonApiTypeConvertor {

@TypeConverter
    fun restoreList(listOfModels: String): List<PokeResultRoomEntity> {
        return Gson().fromJson(listOfModels, object : TypeToken<List<PokeResult>>() {}.type)
    }

    @TypeConverter
    fun saveList(list: List<PokeResultRoomEntity>): String? {
        return Gson().toJson(list)
    }
}