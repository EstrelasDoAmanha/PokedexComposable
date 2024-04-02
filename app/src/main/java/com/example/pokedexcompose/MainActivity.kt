package com.example.pokedexcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.pokedexcompose.ui.navigation.PokemonNavHost
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import com.pokedexcompose.designsystem.components.bottomnavigation.BottomNavigationBar
import com.pokedexcompose.designsystem.components.bottomnavigation.tabs.model.BottomNavigationTabModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                val navController = rememberNavController()
                Surface {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                tabs = listOf(
                                    BottomNavigationTabModel(
                                        title = "Home",
                                        selectedIcon = Icons.Filled.Home,
                                        unselectedIcon = Icons.Outlined.Home,
                                        badgeAmount = null
                                    )
                                ),
                                navController = navController
                            )
                        }
                    ) { padding ->
                        PokemonNavHost(navController)
                    }
                }
            }
        }
    }
}
