package com.example.pokedexcompose.domain.mapper

import com.example.pokedexcompose.domain.model.PokemonListDomain
import com.example.pokedexcompose.data.model.PokemonListDto
import com.example.pokedexcompose.domain.model.ResultDomain
import com.example.pokedexcompose.data.model.ResultDto

internal class MapperPokemonDomainImpl() : MapperPokemonDomain {
    override fun map(from: PokemonListDto): PokemonListDomain {
        return PokemonListDomain(
            count = from.count,
            next = from.next,
            result = transformAtDomainList(from.result)
        )
    }

    private fun transformAtDomainList(from: List<ResultDto>): List<ResultDomain> {
        val result = from.map { resultDto ->
            ResultDomain(
                name = resultDto.name,
                url = resultDto.url,
                gif = resultDto.getGif()
            )
        }

        return result
    }
}
