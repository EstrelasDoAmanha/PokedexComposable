package com.example.pokedexcompose.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.pokedexcompose.ui.details.navigateToPokemonDetails
import com.example.pokedexcompose.ui.details.pokemonDetails
import com.example.pokedexcompose.ui.list.POKEMON_LIST_ROUTE
import com.example.pokedexcompose.ui.list.pokemonList

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PokemonNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    updateTitleTopBar: (String) -> Unit,
    updateTopBarColor: (Color) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = POKEMON_LIST_ROUTE
    ) {
        pokemonList(
            navController = navController,
            modifier = modifier
        ) { pokemonId ->
            navController.navigateToPokemonDetails(pokemonId)
        }
        pokemonDetails(
            modifier = modifier,
            updateTitleTopBar = updateTitleTopBar,
            updateTopBarColor = updateTopBarColor
        )
    }
}
