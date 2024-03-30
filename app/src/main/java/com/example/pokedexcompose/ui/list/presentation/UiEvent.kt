package com.example.pokedexcompose.ui.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface UiEvent {
    data object Loading: UiEvent
    data object Error: UiEvent
    data object LoadingMore: UiEvent
}
