package com.example.pokedexcompose.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

const val pokemonListRoute = "pokemonList"

fun NavGraphBuilder.pokemonList(
) {
    composable(pokemonListRoute) {
        val viewModel = koinViewModel<ListScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState(initial = ListScreenViewModel.ListUiState())
        ListScreen(uiState)
    }
}

fun NavHostController.navigateToDrawTeams(
    navOptions: NavOptions? = null
) {
    navigate(pokemonListRoute, navOptions)
}
