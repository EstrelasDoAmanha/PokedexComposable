package com.example.pokedexcompose.domain.mapper

import com.example.pokedexcompose.domain.model.PokemonListDomain
import com.example.pokedexcompose.data.model.PokemonListDto
import com.example.pokedexcompose.data.model.ResultListDto
import com.example.pokedexcompose.domain.model.ResultListDomain

internal class MapperPokemonDomainImpl() : MapperPokemonDomain {
    override fun map(from: PokemonListDto): PokemonListDomain {
        return PokemonListDomain(
            count = from.count,
            next = from.next,
            result = transformAtDomainList(from.result)
        )
    }

    private fun transformAtDomainList(from: List<ResultListDto>): List<ResultListDomain> {
        return from.map { resultDto ->
            ResultListDomain(
                name = resultDto.name,
                url = resultDto.url,
                gif = resultDto.urlGif
            )
        }
    }
}
