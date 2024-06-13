package com.example.pokedexcompose.ui.list

sealed class UiAction {
    data object GetPokemonList : UiAction()
    data object GetTypeListList : UiAction()
    data class SearchPokemon(val query: String) : UiAction()
    data class SavePosition(val position: Pair<Int, Int>) : UiAction()
    data class ReceiverPosition(val position: Pair<Int, Int>) : UiAction()
    data class LoadMorePokemons(val isLoading:Boolean) : UiAction()
    data class ErrorLoadMorePokemons(val isLoading:Boolean) : UiAction()
}