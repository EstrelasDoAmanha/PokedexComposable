package com.example.pokedexcompose.ui.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokedexcompose.data.repository.firstVisibleItemIndexState
import com.example.pokedexcompose.data.repository.firstVisibleItemScrollOffset
import com.example.pokedexcompose.domain.usecase.PokemonListInteract
import com.example.pokedexcompose.extensions.empty
import com.example.pokedexcompose.ui.list.UiAction
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
        updateState(ListUiState(loading = true))
        dispatcher(UiAction.GetPokemonList)
    }

    fun dispatcher(action: UiAction) {
        viewModelScope.launch(dispatcher.IO) {
            when (action) {
                UiAction.GetPokemonList -> getPokemonList()
                UiAction.GetTypeListList -> getTypeList()
                is UiAction.ReceiverPosition -> receiverPositionState()
                is UiAction.SearchPokemon -> updateListByFilter(action.query)
                is UiAction.SavePosition -> updatePositionState(action.position)
                else -> {}
            }
        }
    }

    private suspend fun getPokemonList() {
        updateState(
            ListUiState(
                result = useCase.getPokemonListUseCase()
                    .cachedIn(viewModelScope)
            )
        )
        dispatcher(UiAction.GetTypeListList)
    }

    private fun updateListByFilter(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updateState(
                uiState.value.copy(
                    filterSelected = query,
                    typeList = markFilterSelected(query),
                    result = useCase.getPokemonListUseCase(query),
                    loading = false
                )
            )
        }
    }

    private fun markFilterSelected(query: String) = uiState.value.typeList.map {
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
        updateState(uiState.value.copy(typeList = useCase.getTypeListUseCase.invoke().results))
    }

    private fun receiverPositionState() {
        viewModelScope.launch(dispatcher.IO) {
            useCase.receiverStateUseCase.invoke().map { preference ->
                val state = preference[firstVisibleItemIndexState] ?: 0
                val offset = preference[firstVisibleItemScrollOffset] ?: 0
                updateState(uiState.value.copy(lastStateList = state to offset))
            }
        }
    }

    private fun updatePositionState(position: Pair<Int, Int>) {
        viewModelScope.launch(dispatcher.IO) {
            useCase.saveStateUseCase(position)
        }
    }

    private fun updateState(state: ListUiState) {
        uiState.update { state }
    }
}
