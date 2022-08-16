package com.example.pokemon.utils

import androidx.room.TypeConverter
import com.example.pokemon.domain.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConvertorResultToString {

    @TypeConverter
    fun fromString(value: String): List<Result> {
        val listType = object : TypeToken<List<Result>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListList(list:  List<Result>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}