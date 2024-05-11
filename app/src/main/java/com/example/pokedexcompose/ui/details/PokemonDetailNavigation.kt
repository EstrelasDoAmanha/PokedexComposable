package com.example.pokedexcompose.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.pokedexcompose.ui.details.presentation.PokemonDetailScreen
import com.example.pokedexcompose.ui.details.presentation.PokemonDetailsUiState
import com.example.pokedexcompose.ui.details.presentation.PokemonDetailsViewModel
import org.koin.androidx.compose.koinViewModel

const val POKEMON_DETAILS_ROUTE = "pokemonDetails"

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.pokemonDetails(id: String) {
    composable(POKEMON_DETAILS_ROUTE) {
        val viewModel = koinViewModel<PokemonDetailsViewModel>()
        val uiState by viewModel.uiState.collectAsState(initial = PokemonDetailsUiState())
        PokemonDetailScreen(uiState)
    }
}

fun NavHostController.navigateToPokemonDetails(navOptions: NavOptions? = null) {
    navigate(POKEMON_DETAILS_ROUTE, navOptions)
}
