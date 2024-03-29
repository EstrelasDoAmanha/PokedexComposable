package com.example.pokedexcompose.ui.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class PokemonListViewModel(
    private val useCase: PokemonUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState

    init {
        viewModelScope.launch {
            getPokemonList()
        }
    }

    private suspend fun getPokemonList() {
        _uiState.update { PokemonListUiState(loading = true) }
        _uiState.update {
            _uiState.value.copy(
                result = useCase.getPokemonList()
            )
        }
        _uiState.update { _uiState.value.copy(loading = false) }
    }
}
