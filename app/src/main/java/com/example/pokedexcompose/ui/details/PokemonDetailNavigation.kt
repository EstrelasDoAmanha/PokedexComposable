package com.example.pokedexcompose.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokedexcompose.ui.details.presentation.PokemonDetailScreen
import com.example.pokedexcompose.ui.details.presentation.PokemonDetailsUiState
import com.example.pokedexcompose.ui.details.presentation.PokemonDetailsViewModel
import org.koin.androidx.compose.koinViewModel

const val POKEMON_DETAILS_HOST = "pokemonDetails/"
const val POKEMON_ID_PARAM = "id"
const val POKEMON_DETAILS_ROUTE = "$POKEMON_DETAILS_HOST{$POKEMON_ID_PARAM}"

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.pokemonDetails(
    modifier: Modifier = Modifier,
    updateTitleTopBar: (String) -> Unit,
    updateTopBarColor: (Color) -> Unit
) {
    composable(
        POKEMON_DETAILS_ROUTE,
        arguments = listOf(navArgument(POKEMON_ID_PARAM) { type = NavType.IntType })
    ) {
        val viewModel = koinViewModel<PokemonDetailsViewModel>()
        val uiState by viewModel.uiState.collectAsState(initial = PokemonDetailsUiState())
        updateTitleTopBar(uiState.pokemonInfo.name.capitalize())
        PokemonDetailScreen(
            uiState = uiState,
            updateTopBarColor = updateTopBarColor,
            modifier = modifier
        ) {
            viewModel.retry()
        }
    }
}

fun NavHostController.navigateToPokemonDetails(id: String, navOptions: NavOptions? = null) {
    navigate("$POKEMON_DETAILS_HOST$id", navOptions)
}
