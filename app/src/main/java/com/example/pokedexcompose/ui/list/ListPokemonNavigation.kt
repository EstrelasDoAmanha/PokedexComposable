package com.example.pokedexcompose.ui.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.pokedexcompose.ui.list.presentation.PokemonListScreen
import com.example.pokedexcompose.ui.list.presentation.PokemonListUiState
import com.example.pokedexcompose.ui.list.presentation.PokemonListViewModel
import org.koin.androidx.compose.koinViewModel

const val POKEMON_LIST_ROUTE = "pokemonList"

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.pokemonList(modifier: Modifier = Modifier) {
    composable(POKEMON_LIST_ROUTE) {
        val viewModel = koinViewModel<PokemonListViewModel>()
        val uiState by viewModel.uiState.collectAsState(initial = PokemonListUiState())
        PokemonListScreen(uiState, modifier = modifier)
    }
}

fun NavHostController.navigateToPokemonList(navOptions: NavOptions? = null) {
    navigate(POKEMON_LIST_ROUTE, navOptions)
}

fun NavHostController.pokemonListRoute() = POKEMON_LIST_ROUTE
