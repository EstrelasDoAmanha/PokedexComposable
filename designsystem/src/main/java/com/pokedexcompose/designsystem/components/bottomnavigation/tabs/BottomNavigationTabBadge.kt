package com.pokedexcompose.designsystem.components.bottomnavigation.tabs

import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationTabBadge(count: Int? = null) {
    count?.let {
        Badge { Text(it.toString()) }
    }
}
