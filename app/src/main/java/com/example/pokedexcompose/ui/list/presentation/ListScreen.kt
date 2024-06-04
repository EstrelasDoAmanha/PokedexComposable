package com.example.pokedexcompose.ui.list.presentation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokedexcompose.common.SHIMMER_LOTTIE_JSON
import com.example.pokedexcompose.domain.model.Type
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
    query: (String) -> Unit,
    iShowFilterActionSheetChange: (Boolean) -> Unit = {},
    iShowFilterActionSheet: Boolean,
    updatePositionState:(Pair<Int, Int>) -> Unit = { },
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
                    query = query,
                    sheetState
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
                    Log.d("TAG", "Saving IndexState: ${snapshot.first}")
                    Log.d("TAG", "Saving Offset: ${snapshot.second}")
                    updatePositionState(snapshot.first to snapshot.second)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    type: List<Type> = emptyList(),
    query: (String) -> Unit = {},
    sheetState: SheetState,
    onDismiss: () -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        FilterList(type, query, onDismiss)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterList(
    type: List<Type>,
    query: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(
            top = 0.dp,
            bottom = 60.dp,
            start = 8.dp,
            end = 8.dp
        )
    ) {
        Text(
            text = "Selecione o tipo do pokemon",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            type.forEach { type ->
                FilterOption(tag = type) {
                    query(type.name)
                    onDismiss()
                }
            }
        }
    }

}


@Composable
fun FilterOption(
    tag: Type,
    onClick: () -> Unit = {}
) {
    Box(
        Modifier
            .clickable {
                onClick()
            }
            .clip(RoundedCornerShape(10.dp))
            .background(if (tag.enabled) Color.Blue else Color.Black)
    ) {
        Text(
            text = tag.name,
            Modifier.padding(
                vertical = 2.dp,
                horizontal = 6.dp
            ),
            color = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FilterBottomSheetPreview() {
    FilterBottomSheet(
        type = listOf(
            Type("Teste 1"),
            (Type("Teste 1")),
            (Type("Teste 1")),
            (Type("Teste 1"))
        ),
        sheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
    )
}

@Preview
@Composable
fun OptionFilterPreview() {
    FilterOption(
        Type(
            name = "Fire",
            enabled = true
        )
    )
}
