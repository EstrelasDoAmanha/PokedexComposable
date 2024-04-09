package com.pokedexcompose.designsystem.components.bottomnavigation.tabs.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationTabModel(
    val title: String,
    val navigateTo: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)
