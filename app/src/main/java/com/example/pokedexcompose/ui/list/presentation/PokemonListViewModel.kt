package com.example.pokedexcompose.ui.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.domain.usecase.PokemonListInteract
import com.example.pokedexcompose.extensions.empty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class PokemonListViewModel(
    private val useCase: PokemonListInteract,
    private val dispatcher: Dispatchers
) : ViewModel() {

    var uiState: MutableStateFlow<ListUiState> = MutableStateFlow(ListUiState())
        private set

    init {
        viewModelScope.launch(dispatcher.IO) {
            updateState(ListUiState(loading = true))
            getPokemonList()
            getTypeList()
            receiverPositionState()
        }
    }

    private suspend fun getPokemonList() {
        updateState(
            ListUiState(
                result = useCase.getPokemonListUseCase(),
                loading = false
            )
        )
    }

    fun updateListByFilter(query: String) {
        updateState(uiState.value.copy(filterSelected = query))
        viewModelScope.launch(Dispatchers.IO) {
            updateState(
                uiState.value.copy(
                    typeList = markFilterSelected(query),
                    result = useCase.getPokemonListUseCase(uiState.value.filterSelected)
                )
            )
        }
    }

    private fun markFilterSelected(query: String) = uiState
        .value.typeList.map {
            if (it.name == query) {
                if (it.enabled) {
                    updateState(uiState.value.copy(filterSelected = String.empty()))
                    it.copy(enabled = false)
                } else {
                    it.copy(enabled = true)
                }
            } else {
                it.copy(enabled = false)
            }
        }

    private suspend fun getTypeList() {
        updateState(
            this.uiState.value.copy(
                typeList = useCase.getTypeListUseCase.invoke().results
            )
        )
    }

    private fun receiverPositionState() {
        viewModelScope.launch(dispatcher.IO) {
            useCase.receiverStateUseCase.invoke().map {
                updateState(uiState.value.copy(lastStateList = it.first to it.second))
            }
        }
    }

    fun updatePositionState(position: Pair<Int, Int>) {
        viewModelScope.launch(dispatcher.IO) {
            useCase.saveStateUseCase(position)
        }
    }

    private fun updateState(state: ListUiState) {
        uiState.update { state }
    }
}
