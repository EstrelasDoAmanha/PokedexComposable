package com.example.pokedexcompose.domain.usecase.typelist

import com.example.pokedexcompose.domain.model.TypeListDomain

internal interface GetTypesListUseCase {
    suspend fun invoke(): TypeListDomain
}
