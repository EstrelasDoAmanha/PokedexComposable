package com.example.pokedexcompose.data.mappers

import com.example.pokedexcompose.data.model.Statistics
import com.example.pokedexcompose.domain.model.PokemonStatistics

class PokemonStatsDtoToDomain : Mapper<List<Statistics>, List<PokemonStatistics>> {
    override fun map(from: List<Statistics>): List<PokemonStatistics> {
        //fixme: tratar lista vazia
        return from.map { statsDto ->
            PokemonStatistics(
                name = statsDto.stat.name,
                baseStat = statsDto.baseStat
            )
        }
    }
}