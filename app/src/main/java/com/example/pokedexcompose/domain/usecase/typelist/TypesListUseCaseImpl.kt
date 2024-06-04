package com.example.pokedexcompose.domain.usecase.typelist

import com.example.pokedexcompose.domain.model.TypeListDomain
import com.example.pokedexcompose.domain.repository.PokemonRepository

internal class TypesListUseCaseImpl(
    private val repository: PokemonRepository
) : TypesListUseCase {
    override suspend fun invoke(): TypeListDomain {
        return repository.getTypeList()
    }
}
