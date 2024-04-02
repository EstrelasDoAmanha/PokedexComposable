package com.pokedexcompose.designsystem.components.bottomnavigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.pokedexcompose.designsystem.components.bottomnavigation.tabs.BottomNavigationTab
import com.pokedexcompose.designsystem.components.bottomnavigation.tabs.model.BottomNavigationTabModel

@Composable
fun BottomNavigationBar(tabs: List<BottomNavigationTabModel>, navController: NavController) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    NavigationBar {
        tabs.forEachIndexed { index, tabModel ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    navController.navigate(tabModel.title)
                },
                icon = {
                    BottomNavigationTab(
                        isSelected = selectedTabIndex == index,
                        selectedIcon = tabModel.selectedIcon,
                        unselectedIcon = tabModel.unselectedIcon,
                        title = tabModel.title,
                        badgeAmount = tabModel.badgeAmount
                    )
                },
                label = { Text(tabModel.title) }
            )
        }
    }
}
