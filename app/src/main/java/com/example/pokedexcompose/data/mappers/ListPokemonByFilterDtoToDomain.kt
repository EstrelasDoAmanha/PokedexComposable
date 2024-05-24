package com.example.pokedexcompose.data.mappers

import com.example.pokedexcompose.data.model.ListPokemonByTypesDto
import com.example.pokedexcompose.data.model.ResultListDto
import com.example.pokedexcompose.domain.model.ResultListDomain

class ListPokemonByFilterDtoToDomain{
    fun map(from: ListPokemonByTypesDto): List<ResultListDto> {
        return from.results.map {
            ResultListDto(name = it.pokemon.name, url = it.pokemon.url)
        }
    }

}
