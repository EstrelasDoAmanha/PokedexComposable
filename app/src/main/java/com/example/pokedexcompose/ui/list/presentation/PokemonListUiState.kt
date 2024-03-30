package com.example.pokedexcompose.ui.list.presentation

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.PokemonListDomain
import com.example.pokedexcompose.domain.model.ResultListDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class PokemonListUiState(
    val loading: Boolean = false,
    val result: Flow<PagingData<ResultListDomain>> = flowOf()
)
