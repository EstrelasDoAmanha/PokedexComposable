package com.example.pokedexcompose.domain.usecase

import com.example.pokedexcompose.domain.usecase.pokemonlist.ListUseCase
import com.example.pokedexcompose.domain.usecase.storagestate.StorageStateUseCase
import com.example.pokedexcompose.domain.usecase.typelist.TypesListUseCase

internal class PokemonInteract(
   val pokemonListUseCase: ListUseCase,
   val typeListUseCase: TypesListUseCase,
   val storageStateUseCase: StorageStateUseCase,
)
