package com.example.pokedexcompose.ui.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.data.local.results
import com.example.pokedexcompose.data.model.Pokemon
import com.example.pokedexcompose.ui.list.usecase.ListScreenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class ListScreenViewModel(
    private val useuCase: ListScreenUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(ListUiState(results))
    val uiState : StateFlow<ListUiState> = _uiState


    init {
        viewModelScope.launch {
            getList()
        }
    }

    private suspend fun getList() = useuCase.getList()

    class ListUiState(
        val list: List<Pokemon> = results
    )

}
