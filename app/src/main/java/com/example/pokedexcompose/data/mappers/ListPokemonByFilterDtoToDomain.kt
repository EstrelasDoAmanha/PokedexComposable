package com.example.pokedexcompose.data.mappers

import com.example.pokedexcompose.data.model.ListPokemonByTypesDto
import com.example.pokedexcompose.domain.model.ResultListDomain

class ListPokemonByFilterDtoToDomain{
    fun map(from: ListPokemonByTypesDto): List<ResultListDomain> {
        return from.results.map {
            ResultListDomain(name = it.pokemon.name)
        }
    }

}
