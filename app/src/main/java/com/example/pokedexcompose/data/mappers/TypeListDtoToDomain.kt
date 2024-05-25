package com.example.pokedexcompose.data.mappers

import com.example.pokedexcompose.data.model.TypeListDto
import com.example.pokedexcompose.data.model.TypeDto
import com.example.pokedexcompose.domain.mapper.Mapper
import com.example.pokedexcompose.domain.model.Type
import com.example.pokedexcompose.domain.model.TypeListDomain
class TypeListDtoToDomain : Mapper<TypeListDto, TypeListDomain> {
    override fun map(from: TypeListDto): TypeListDomain {
        return TypeListDomain(
            results = from.results.toDomain()
        )
    }

    private fun List<TypeDto>.toDomain() = this.map {
        Type(name = it.name.orEmpty())
    }
}
