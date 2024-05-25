package com.example.pokedexcompose.data.database

import androidx.room.TypeConverter
import com.example.pokedexcompose.data.model.ResultListDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromResultListDtoList(value: List<ResultListDto>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toResultListDtoList(value: String): List<ResultListDto> {
        return Gson().fromJson(value, object : TypeToken<List<ResultListDto>>() {}.type)
    }

    @TypeConverter
    fun fromPokemonDbListDtoList(value: List<PokemonDb>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toPokemonDbListDtoList(value: String): List<PokemonDb> {
        return Gson().fromJson(value, object : TypeToken<List<PokemonDb>>() {}.type)
    }

}