package com.example.pokedexcompose.ui.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.pokedexcompose.ui.list.presentation.ListUiState
import com.example.pokedexcompose.ui.list.presentation.PokemonListScreen
import com.example.pokedexcompose.ui.list.presentation.PokemonListViewModel
import org.koin.androidx.compose.koinViewModel

const val POKEMON_LIST_ROUTE = "pokemonList"

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.pokemonList(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    updateTitleTopBar: (String) -> Unit,
    updateTopBarColor: (Color) -> Unit,
    iShowFilterActionSheet: Boolean,
    iShowFilterActionSheetChange: (Boolean) -> Unit,
    toPokemonDetails: (String) -> Unit
) {
    composable(POKEMON_LIST_ROUTE) {
        val viewModel = koinViewModel<PokemonListViewModel>()
        val uiState by viewModel.uiState.collectAsState(initial = ListUiState())
        updateTitleTopBar("Lista de Pokémons")
        updateTopBarColor(Color.White)
        PokemonListScreen(
            uiState = uiState,
            modifier = modifier,
            iShowFilterActionSheet = iShowFilterActionSheet,
            query = {
                viewModel.updateListByFilter(it)
            },
            iShowFilterActionSheetChange = iShowFilterActionSheetChange,
            updatePositionState = {
                viewModel.updatePositionState(it.first to it.second)
            }
        ) { id ->
            toPokemonDetails(id)
        }
    }
}

fun NavHostController.navigateToPokemonList(navOptions: NavOptions? = null) {
    navigate(POKEMON_LIST_ROUTE, navOptions)
}

fun NavHostController.pokemonListRoute() = POKEMON_LIST_ROUTE
