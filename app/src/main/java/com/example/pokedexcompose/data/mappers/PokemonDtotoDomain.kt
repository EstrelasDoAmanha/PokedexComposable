package com.example.pokedexcompose.data.mappers

import com.example.pokedexcompose.data.model.PokemonDto
import com.example.pokedexcompose.domain.model.PokemonInfo

class PokemonDtotoDomain(
    private val pokemonStatsDtoToDomain: PokemonStatsDtoToDomain,
    private val pokemonTypeDtoToDomain: PokemonTypeDtoToDomain
): Mapper<List<PokemonDto>,List<PokemonInfo>> {
    override fun map(from: List<PokemonDto>): List<PokemonInfo> {
        //fixme: tratar lista vazia
        return from.map {dto->
            PokemonInfo(
                name = dto.name,
                id = dto.id,
                order = dto.order,
                weight = dto.weight,
                height = dto.height,
                image = getPokemonImage(dto),
                stats = pokemonStatsDtoToDomain.map(dto.stats),
                type = pokemonTypeDtoToDomain.map(dto.types)
            )
        }
    }

    private fun getPokemonImage(dto: PokemonDto): String {
        //fixme: Mover url para constants e criar regr apara replace do id
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/shiny/${dto.id}.gif"
    }
}