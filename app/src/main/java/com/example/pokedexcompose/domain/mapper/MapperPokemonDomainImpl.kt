package com.example.pokedexcompose.domain.mapper

import com.example.pokedexcompose.data.model.PokemonListDto
import com.example.pokedexcompose.data.model.ResultListDto
import com.example.pokedexcompose.domain.model.PokemonListDomain
import com.example.pokedexcompose.domain.model.ResultListDomain

internal class MapperPokemonDomainImpl() : Mapper<PokemonListDto, PokemonListDomain> {
    override fun map(from: PokemonListDto): PokemonListDomain {
        return PokemonListDomain(
            count = from.count,
            next = from.next,
            result = from.result.toDomain()
        )
    }

    private fun List<ResultListDto>.toDomain(): List<ResultListDomain> {
        return this.map { resultDto ->
            ResultListDomain(
                name = resultDto.name,
                url = resultDto.url,
                gif = resultDto.urlGif
            )
        }
    }

}
