package com.example.pokedexcompose.ui.list

import androidx.lifecycle.ViewModel
import com.example.pokedexcompose.data.local.results
import com.example.pokedexcompose.domain.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow

class ListScreenViewModel() : ViewModel() {

    var uiState = MutableStateFlow<ListUiState>(ListUiState(results))
        private set

    class ListUiState(
        val list: List<Pokemon> = results
    )

}
