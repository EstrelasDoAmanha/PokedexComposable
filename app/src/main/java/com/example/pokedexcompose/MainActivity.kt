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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.pokedexcompose.ui.list.pokemonListRoute
import com.example.pokedexcompose.ui.navigation.PokemonNavHost
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import com.pokedexcompose.designsystem.components.bottomnavigation.BottomNavigationBar
import com.pokedexcompose.designsystem.components.bottomnavigation.tabs.model.BottomNavigationTabModel

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                val navController = rememberNavController()
                var title by remember {
                    mutableStateOf("Pokemon")
                }
                Surface {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = title)
                                }
                            )
                        },
                        bottomBar = {
                            BottomNavigationBar(
                                tabs = listOf(
                                    BottomNavigationTabModel(
                                        title = "Home",
                                        selectedIcon = Icons.Filled.Home,
                                        unselectedIcon = Icons.Outlined.Home,
                                        badgeAmount = null,
                                        navigateTo = navController.pokemonListRoute()
                                    )
                                ),
                                navController = navController
                            )
                        }
                    ) { padding ->
                        PokemonNavHost(
                            navController,
                            modifier = Modifier.padding(padding)
                        ) { newTitle ->
                            title = newTitle
                        }
                    }
                }
            }
        }
    }
}
