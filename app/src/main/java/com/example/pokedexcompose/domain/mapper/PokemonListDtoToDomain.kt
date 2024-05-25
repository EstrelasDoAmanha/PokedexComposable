package com.example.pokedexcompose.domain.mapper

import com.example.pokedexcompose.data.database.PokemonDb
import com.example.pokedexcompose.domain.model.PokemonListDomain
import com.example.pokedexcompose.domain.model.ResultListDomain

class PokemonListDtoToDomain :
    Mapper<List<PokemonDb>, PokemonListDomain> {
    override fun map(from: List<PokemonDb>): PokemonListDomain {
        val listToDomain = from.map {
            ResultListDomain(
                name = it.name.orEmpty(),
                url = it.url.orEmpty(),
                gif = it.gif.orEmpty(),
                id = it.id
            )
        }
        return PokemonListDomain(
            result = listToDomain
        )
    }
}
