package com.example.pokedexcompose.ui.list.presentation

sealed interface UiEvent {
    data object Loading : UiEvent
    data object Error : UiEvent
    data object LoadingMore : UiEvent
}
