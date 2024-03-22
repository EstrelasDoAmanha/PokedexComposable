package com.example.pokedexcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.pokedexcompose.ui.navigation.PokemonNavHost
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                val navController = rememberNavController()
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) { PokemonNavHost(navController) }
            }
        }
    }
}
