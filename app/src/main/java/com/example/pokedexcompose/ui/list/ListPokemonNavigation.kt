package com.example.pokedexcompose.ui.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

const val pokemonListRoute = "pokemonList"

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.pokemonList(
) {
    composable(pokemonListRoute) {
        val viewModel = koinViewModel<ListScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState(initial = ListScreenViewModel.ListUiState())
        ListScreen(uiState)
    }
}

fun NavHostController.navigateToPokemonList(
    navOptions: NavOptions? = null
) {
    navigate(pokemonListRoute, navOptions)
}
