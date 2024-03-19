package com.example.pokedexcompose.data.mappers

import com.example.pokedexcompose.data.model.TypeDto
import com.example.pokedexcompose.domain.model.PokemonType

class PokemonTypeDtoToDomain : Mapper<List<TypeDto>, List<PokemonType>> {
    override fun map(from: List<TypeDto>): List<PokemonType> {
        return if (from.isNotEmpty()) {
            from.map { typeDto ->
                PokemonType(
                    name = typeDto.name
                )
            }
        } else {
            emptyList()
        }
    }
}