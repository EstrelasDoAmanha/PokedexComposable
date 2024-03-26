package com.example.pokedexcompose.domain.mapper

import com.example.pokedexcompose.domain.model.PokemonListDomain
import com.example.pokedexcompose.data.model.PokemonListDto

internal interface MapperPokemonDomain : Mapper<PokemonListDto, PokemonListDomain> {
    override fun map(from: PokemonListDto): PokemonListDomain
}
