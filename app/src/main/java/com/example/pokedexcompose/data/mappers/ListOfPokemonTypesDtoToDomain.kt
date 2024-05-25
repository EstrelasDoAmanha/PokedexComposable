package com.example.pokedexcompose.data.mappers

import com.example.pokedexcompose.data.model.ListPokemonTypesDto
import com.example.pokedexcompose.data.model.TypeDto
import com.example.pokedexcompose.domain.mapper.Mapper
import com.example.pokedexcompose.domain.model.TypeListDomain
import com.example.pokedexcompose.domain.model.Type
class ListOfPokemonTypesDtoToDomain : Mapper<ListPokemonTypesDto, TypeListDomain> {
    override fun map(from: ListPokemonTypesDto): TypeListDomain {
        return TypeListDomain(
           results =  from.results.toDomain()
        )
    }

     private fun List<TypeDto>.toDomain() = this.map {
         Type(name = it.name.orEmpty())
     }

}
