package com.example.pokedexcompose.ui.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.usecase.GetPokemonDetailsUseCase
import com.example.pokedexcompose.ui.details.POKEMON_ID_PARAM
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(
    private val pokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var pokemonId: Int = -1
    private var _uiState = MutableStateFlow(PokemonDetailsUiState())
    val uiState: StateFlow<PokemonDetailsUiState> = _uiState

    init {
        getPokemonId()
    }

    private fun getPokemonId() {
        pokemonId = savedStateHandle[POKEMON_ID_PARAM] ?: Random.nextInt(0, 250)
        getPokemonDetails(pokemonId)
    }

    private fun getPokemonDetails(pokemonId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            pokemonDetailsUseCase(pokemonId)
                .catch { handleError(it) }
                .onCompletion { resetLoading() }
                .collect { handleSuccess(it) }
        }
    }

    fun retry() {
        getPokemonDetails(pokemonId)
    }

    private fun handleError(exception: Throwable) {
        _uiState.update {
            it.copy(
                isLoading = false,
                isError = true
            )
        }
    }

    private fun resetLoading() {
        _uiState.update {
            it.copy(
                isLoading = false
            )
        }
    }

    private fun handleSuccess(pokemonInfo: PokemonInfo) {
        _uiState.update {
            it.copy(
                isLoading = false,
                isError = false,
                pokemonInfo = pokemonInfo
            )
        }
    }
}
