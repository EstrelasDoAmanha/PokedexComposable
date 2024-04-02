package com.example.pokedexcompose.data.mappers

import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.domain.model.PokemonInfo

class PokemonDtotoDomain(
    private val pokemonStatsDtoToDomain: PokemonStatsDtoToDomain,
    private val pokemonTypeDtoToDomain: PokemonTypeDtoToDomain
) : Mapper<PokemonDto, PokemonInfo> {
    override fun map(from: PokemonDto): PokemonInfo {
        return PokemonInfo(
            name = from.name.orEmpty(),
            id = from.id,
            order = from.order,
            weight = from.weight,
            height = from.height,
            image = getPokemonImage(from),
            stats = pokemonStatsDtoToDomain.map(from.stats),
            type = pokemonTypeDtoToDomain.map(from.types)
        )
    }

    private fun getPokemonImage(dto: PokemonDto): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/" +
            "showdown/shiny/${dto.id}.gif"
    }
}
