package com.example.pokedexcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonDb::class], version = 1)
abstract class PokemonDataBase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
