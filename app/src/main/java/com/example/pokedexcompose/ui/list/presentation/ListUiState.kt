package com.example.pokedexcompose.ui.list.presentation

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.model.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ListUiState(
    val loading: Boolean = false,
    var result: Flow<PagingData<ResultListDomain>> = flowOf(),
    var typeList: List<Type> = emptyList(),
    val itemsFiltered: List<ResultListDomain> = emptyList(),
    val lastStateList: Pair<Int, Int> = Pair(0, 0)
)
