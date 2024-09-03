package com.example.pokedexcompose.domain.usecase

import com.example.pokedexcompose.domain.usecase.pokemonlist.GetListUseCase
import com.example.pokedexcompose.domain.usecase.storagestate.ReceiverStateUseCase
import com.example.pokedexcompose.domain.usecase.storagestate.SaveStateUseCase
import com.example.pokedexcompose.domain.usecase.typelist.GetTypesListUseCase

internal class PokemonListInteract(
    val getPokemonListUseCase: GetListUseCase,
    val getTypeListUseCase: GetTypesListUseCase,
    val saveStateUseCase: SaveStateUseCase,
    val receiverStateUseCase: ReceiverStateUseCase
)
