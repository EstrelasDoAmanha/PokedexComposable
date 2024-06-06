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
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                val initialColor = MaterialTheme.colorScheme.surfaceContainer
                val initialTitle = stringResource(R.string.main_title)
                var title by remember {
                    mutableStateOf(initialTitle)
                }
                var topBarColor by remember {
                    mutableStateOf(initialColor)
                }
                var iShowFilterActionSheet by remember {
                    mutableStateOf(false)
                }
                Surface {
                    Scaffold(topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = title,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 28.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.White
                                )
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        iShowFilterActionSheet = !iShowFilterActionSheet
                                    },
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)

                                ) {
                                    Icon(
                                        Icons.Outlined.Settings,
                                        contentDescription = "Navigation icon",
                                        tint = Color.Gray
                                    )
                                }
                            },
                            colors = TopAppBarColors(
                                containerColor = topBarColor,
                                scrolledContainerColor = initialColor,
                                navigationIconContentColor = initialColor,
                                titleContentColor = initialColor,
                                actionIconContentColor = initialColor
                            )
                        )
                    }, bottomBar = {
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
                    }) { padding ->
                        PokemonNavHost(
                            navController = navController,
                            updateTitleTopBar = { newTitle ->
                                title = newTitle
                            },
                            updateTopBarColor = { color ->
                                topBarColor = color
                            },
                            iShowFilterActionSheet = iShowFilterActionSheet,
                            iShowFilterActionSheetChange = {
                                iShowFilterActionSheet = it
                            },
                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            }
        }
    }
}
