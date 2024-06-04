package com.example.pokedexcompose.ui.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokedexcompose.domain.usecase.PokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class PokemonListViewModel(
    private val useCase: PokemonUseCase
) : ViewModel() {

    var uiState: MutableStateFlow<ListUiState> = MutableStateFlow(ListUiState())
        private set

    init {
        viewModelScope.launch {
            updateState(ListUiState(loading = true))
            getPokemonList()
            getTypeList()
            receiverPositionState()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "view modeld onCleared: ")
    }


    private suspend fun getPokemonList() {
        updateState(
            ListUiState(
                result = useCase.getPokemonList(),
                loading = false
            )
        )
    }

    private suspend fun getTypeList() {
        updateState(
            this.uiState.value.copy(
                typeList = useCase.getTypeList().results
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
                    result = useCase.getPokemonList(resetFilter),
                    typeList = listType
                )
            )
        }
    }

    private fun receiverPositionState() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.receiverPositionState().map {
                updateState(uiState.value.copy(lastStateList = it.first to it.second))
            }
        }
    }

    fun updatePositionState(position: Pair<Int, Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.updatePositionState(position)
        }
    }

    private fun updateState(state: ListUiState) {
        uiState.update { state }
    }
}
