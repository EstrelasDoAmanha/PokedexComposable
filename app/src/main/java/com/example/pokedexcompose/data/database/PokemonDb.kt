package com.example.pokedexcompose.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonDb(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "gif") val gif: String?,
    @ColumnInfo(name = "type") val type: String?
)
