package com.example.pokedexcompose.ui.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.usecase.PokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.log

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
        updateState(PokemonListUiState(result = useCase.getPokemonList(), loading = false))
    }

    private suspend fun getPokemonListOfPokemonTypesList() {
        updateState(
            _uiState.value.copy(
                typeList = useCase.ListOfPokemonTypes().results
            )
        )
    }

    private suspend fun addSlot() {
//        _uiState.value.result.combine { pokemonList ->
//https://proandroiddev.com/pagination-with-paging-3-modifying-paged-data-223f0bea603b
//        }
    }


    fun selectFilter(it: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultRequest = useCase.listPokemonByFilter(it)
            val castPaging = PagingData.from(resultRequest)
            val newPaging = flow<PagingData<ResultListDomain>>{castPaging }
            Log.d("TAG", "resultRequest: ${resultRequest}")
            Log.d("TAG", "castPaging: ${castPaging}")
            Log.d("TAG", "newPaging: ${newPaging}")
            updateState(_uiState.value.copy(result = newPaging))
        }
    }

    private suspend fun updateState(state: PokemonListUiState)  {
        _uiState.update { state}
    }
}
