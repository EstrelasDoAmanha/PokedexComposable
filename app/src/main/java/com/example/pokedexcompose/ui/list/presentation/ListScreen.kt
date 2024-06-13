package com.example.pokedexcompose.ui.list.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadType
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokedexcompose.common.SHIMMER_LOTTIE_JSON
import com.example.pokedexcompose.ui.list.UiAction
import com.example.pokedexcompose.ui.list.presentation.component.FilterBottomSheet
import com.example.pokedexcompose.ui.list.presentation.component.PokemonItem
import com.pokedexcompose.designsystem.components.loading.Lottie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
internal fun PokemonListScreen(
    uiState: ListUiState,
    modifier: Modifier = Modifier,
    uiAction: (UiAction) -> Unit,
    iShowFilterActionSheetChange: (Boolean) -> Unit = {},
    iShowFilterActionSheet: Boolean,
    onItemClick: (String) -> Unit = {}
) {
    Column(Modifier.background(Color.White)) {
        if (uiState.loading) {
            Lottie(url = SHIMMER_LOTTIE_JSON)
        } else {
            val lazyPokemon = uiState.result.collectAsLazyPagingItems()
            val scope = rememberCoroutineScope()
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            val rememberLazyGridState =
                rememberLazyGridState(
                    initialFirstVisibleItemIndex = uiState.lastStateList.first,
                    initialFirstVisibleItemScrollOffset = uiState.lastStateList.second
                )
            if (iShowFilterActionSheet) {
                FilterBottomSheet(
                    type = uiState.typeList,
                    query = {
                        uiAction(UiAction.SearchPokemon(it))
                    },
                    sheetState = sheetState
                ) {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            iShowFilterActionSheetChange(false)
                        }
                    }
                }
            }

            LaunchedEffect(rememberLazyGridState) {
                snapshotFlow {
                    rememberLazyGridState.firstVisibleItemIndex to
                            rememberLazyGridState.firstVisibleItemScrollOffset
                }.debounce(500L).collectLatest { snapshot ->
                }
            }


            LazyVerticalGrid(
                columns = GridCells.Adaptive(128.dp),
                modifier = modifier,
                state = rememberLazyGridState,
                contentPadding = PaddingValues(
                    vertical = 16.dp,
                    horizontal = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(lazyPokemon.itemCount) { index ->
                        PokemonItem(
                            index = index,
                            onItemClick = onItemClick,
                            lazyPokemon = lazyPokemon
                        )
                    }
                }
            )
        }
    }
}
