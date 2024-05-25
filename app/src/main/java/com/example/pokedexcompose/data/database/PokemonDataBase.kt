package com.example.pokedexcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PokemonDb::class], version = 2)
abstract class PokemonDataBase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}