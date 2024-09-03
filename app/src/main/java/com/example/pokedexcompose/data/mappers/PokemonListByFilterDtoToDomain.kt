package com.example.pokedexcompose.data.mappers

import com.example.pokedexcompose.data.model.PokemonByTypesDto
import com.example.pokedexcompose.data.model.ResultListDto

class PokemonListByFilterDtoToDomain {
    fun map(from: PokemonByTypesDto): List<ResultListDto> {
        return from.results.map {
            ResultListDto(name = it.pokemon.name, url = it.pokemon.url)
        }
    }
}
