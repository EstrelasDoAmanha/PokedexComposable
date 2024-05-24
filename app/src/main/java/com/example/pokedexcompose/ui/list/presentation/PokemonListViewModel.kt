package com.example.pokedexcompose.ui.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.usecase.PokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
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
            getPokemonListOfPokemonTypesList()
        }
    }

    private suspend fun getPokemonList() {
        updateState(PokemonListUiState(
            result = useCase.getPokemonList(),
            loading = false
        ))
    }

    private suspend fun getPokemonListOfPokemonTypesList() {
        updateState(
            _uiState.value.copy(
                typeList = useCase.ListOfPokemonTypes().results
            )
        )
    }

    fun selectFilter(filter: String) {
        var isClearList = filter
        val listType = _uiState.value.typeList.map {
            if(it.name == filter){
                if(it.enabled){
                    isClearList = ""
                    it.copy(enabled = false)
                }else {
                    it.copy(enabled = true)
                }
            }else{
                it.copy(enabled = false)
            }

        }
        viewModelScope.launch(Dispatchers.IO) {
            updateState(_uiState.value.copy(
                result = useCase.getPokemonList(isClearList),
                typeList = listType
            ))
        }
    }

    private fun updateState(state: PokemonListUiState) {
        _uiState.update { state }
    }
}
