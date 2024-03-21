package com.example.pokedexcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.pokedexcompose.ui.list.pokemonList
import com.example.pokedexcompose.ui.list.POKEMON_LIST_ROUTE

@Composable
fun PokemonNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = POKEMON_LIST_ROUTE
    ) {
        pokemonList()
    }
}
