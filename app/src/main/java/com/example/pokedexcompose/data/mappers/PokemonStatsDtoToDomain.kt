package com.example.pokedexcompose.data.mappers

import com.example.pokedexcompose.domain.model.PokemonStatistics
import com.example.pokedexcompose.data.model.Statistics

class PokemonStatsDtoToDomain : Mapper<List<Statistics>, List<PokemonStatistics>> {
    override fun map(from: List<Statistics>): List<PokemonStatistics> {
        return if (from.isNotEmpty()) {
            from.map { statsDto ->
                PokemonStatistics(
                    name = statsDto.stat.name.orEmpty(),
                    baseStat = statsDto.baseStat
                )
            }
        } else {
            emptyList()
        }
    }
}