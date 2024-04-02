package com.example.pokedexcompose.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.pokedexcompose.ui.list.POKEMON_LIST_ROUTE
import com.example.pokedexcompose.ui.list.pokemonList

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PokemonNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = POKEMON_LIST_ROUTE
    ) {
        pokemonList()
    }
}
