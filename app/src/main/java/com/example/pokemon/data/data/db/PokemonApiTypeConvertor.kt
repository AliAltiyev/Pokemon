package com.example.pokemon.data.data.db

import androidx.room.TypeConverter
import com.example.pokemon.domain.PokeResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class PokemonApiTypeConvertor {

    @TypeConverter
    fun restoreList(listOfModels: String): List<PokeResult> {
        return Gson().fromJson(listOfModels, object : TypeToken<List<PokeResult>>() {}.type)
    }

    @TypeConverter
    fun saveList(list: List<PokeResult>): String? {
        return Gson().toJson(list)
    }
}