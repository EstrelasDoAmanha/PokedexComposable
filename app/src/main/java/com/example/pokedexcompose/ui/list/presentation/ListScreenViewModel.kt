package com.example.pokedexcompose.ui.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.ui.list.usecase.ListScreenUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ListScreenViewModel(
    private val useCase: ListScreenUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(ListUiState())
    val uiState : StateFlow<ListUiState> = _uiState


    init {
        viewModelScope.launch {
            getPokemon()
        }
    }

    private suspend fun getPokemon(){
        _uiState.update {
            ListUiState(loading = true)
        }

        _uiState.update {
            _uiState.value.copy(pokemonDto = useCase.getPokemon())
        }

        delay(1500)

        _uiState.update {
            _uiState.value.copy(loading = false)
        }
    }

}
