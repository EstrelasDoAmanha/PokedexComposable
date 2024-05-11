package com.example.pokedexcompose.ui.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.domain.usecase.GetPokemonDetailsUseCase
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
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
        pokemonId = savedStateHandle[ARG_POKEMON_ID] ?: Random.nextInt(0, 250)
        getPokemonDetails(pokemonId)
    }

    private fun getPokemonDetails(pokemonId: Int) {
        viewModelScope.launch {
            pokemonDetailsUseCase(pokemonId)
                .onEach {
                }
                .catch {
                }
        }
    }

    companion object {
        const val ARG_POKEMON_ID = "POKEMON_ID"
    }
}
