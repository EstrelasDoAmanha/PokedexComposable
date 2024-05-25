package com.example.pokedexcompose.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedexcompose.data.model.ResultListDto

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonDb WHERE :search IS NULL OR :search IS NOT NULL AND tipo like '%'||:search||'%' LIMIT :limit OFFSET :offset")
    fun getAll(offset: Int = 0, limit: Int = 20, search:String = ""): List<PokemonDb>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemons: List<PokemonDb>)

    @Query("DELETE FROM pokemondb")
    fun deleteAll()

}