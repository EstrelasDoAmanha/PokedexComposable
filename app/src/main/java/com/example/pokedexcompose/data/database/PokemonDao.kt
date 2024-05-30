package com.example.pokedexcompose.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Query(
        "SELECT * FROM PokemonDb WHERE :search IS NULL OR " +
            ":search IS NOT NULL AND type " +
            "like '%'||:search||'%' LIMIT :limit OFFSET :offset"
    )
    fun getPokemonList(offset: Int = 0, limit: Int = 20, search: String = ""): List<PokemonDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemons(pokemons: List<PokemonDb>)
}
