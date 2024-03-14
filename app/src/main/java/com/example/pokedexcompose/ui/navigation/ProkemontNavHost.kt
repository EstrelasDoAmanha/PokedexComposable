package com.example.pokedexcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.pokedexcompose.ui.list.pokemonList
import com.example.pokedexcompose.ui.list.pokemonListRoute

@Composable
fun ProkemontNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = pokemonListRoute
    ) {
        pokemonList()
    }
}
