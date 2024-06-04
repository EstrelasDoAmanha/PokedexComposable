package com.example.pokedexcompose.ui.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.domain.usecase.PokemonListInteract
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

    private suspend fun getTypeList() {
        updateState(
            this.uiState.value.copy(
                typeList = useCase.getTypeListUseCase.invoke().results
            )
        )
    }

    fun updateListByFilter(query: String) {
        var resetFilter = query
        val listType = this.uiState.value.typeList.map {
            if (it.name == query) {
                if (it.enabled) {
                    resetFilter = ""
                    it.copy(enabled = false)
                } else {
                    it.copy(enabled = true)
                }
            } else {
                it.copy(enabled = false)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            updateState(
                this@PokemonListViewModel.uiState.value.copy(
                    result = useCase.getPokemonListUseCase(resetFilter),
                    typeList = listType
                )
            )
        }
    }

    private fun receiverPositionState() {
        viewModelScope.launch(dispatcher.IO) {
            useCase.saveStateUseCase.invoke().map {
                updateState(uiState.value.copy(lastStateList = it.first to it.second))
            }
        }
    }

    fun updatePositionState(position: Pair<Int, Int>) {
        viewModelScope.launch(dispatcher.IO) {
            useCase.saveStateUseCase.updatePositionState(position)
        }
    }

    private fun updateState(state: ListUiState) {
        uiState.update { state }
    }
}
