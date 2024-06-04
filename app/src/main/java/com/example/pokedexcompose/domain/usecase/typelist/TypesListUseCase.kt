package com.example.pokedexcompose.domain.usecase.typelist

import com.example.pokedexcompose.domain.model.TypeListDomain

internal interface TypesListUseCase {
    suspend fun invoke(): TypeListDomain
}
