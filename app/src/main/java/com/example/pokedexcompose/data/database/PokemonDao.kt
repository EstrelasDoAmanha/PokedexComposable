package com.example.pokedexcompose.data.database

import androidx.paging.PagingData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query(
        "SELECT * FROM PokemonEntity WHERE :search IS NULL OR " +
            ":search IS NOT NULL AND type " +
            "like '%'||:search||'%' LIMIT :limit OFFSET :offset"
    )
    suspend fun getPokemonListByFilter(
        offset: Int = 0,
        limit: Int = 20,
        search: String = ""
    ): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<PokemonEntity>)
}
