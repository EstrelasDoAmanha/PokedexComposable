package com.example.pokedexcompose.ui.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            updateState(PokemonListUiState(loading = true))
            getPokemonList()
        }
    }

    private suspend fun getPokemonList() {
        updateState(PokemonListUiState(result = useCase.getPokemonList(), loading = false))
    }

    private suspend fun updateState(state:PokemonListUiState){
        _uiState.emit(state)
    }
}
