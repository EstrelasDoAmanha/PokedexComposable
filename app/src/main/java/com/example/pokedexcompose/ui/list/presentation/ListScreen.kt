package com.example.pokedexcompose.ui.list.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.domain.model.Type
import com.example.pokedexcompose.extensions.empty
import com.pokedexcompose.designsystem.components.loading.Lottie
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
internal fun PokemonListScreen(
    uiState: ListUiState,
    modifier: Modifier = Modifier,
    query: (String) -> Unit
) {
    if (uiState.loading) {
        Lottie(url = SHIMMER_LOTTIE_JSON)
    } else {
        val lazyPokemon: LazyPagingItems<ResultListDomain> = uiState.result.collectAsLazyPagingItems()
        var isShowBottomSheet by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState()
        Column {
            Row(
                modifier = Modifier
                    .clickable {
                        isShowBottomSheet = true
                    }
                    .padding(
                        end = 16.dp,
                        start = 16.dp,
                        top = 16.dp
                    )
            ) {
                Text(text = "Filtro")
                Icon(
                    Icons.Outlined.Settings,
                    contentDescription = null,
                    modifier = Modifier
                )
            }

            if (isShowBottomSheet) {
                FilterBottomSheet(
                    type = uiState.typeList,
                    query = query,
                    sheetState
                ) {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            isShowBottomSheet = false
                        }
                    }
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(128.dp),
                modifier = modifier,
                contentPadding = PaddingValues(
                    vertical = 16.dp,
                    horizontal = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(lazyPokemon.itemCount) { index ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(lazyPokemon[index]?.gif ?: String.empty())
                                        .decoderFactory(ImageDecoderDecoder.Factory()).build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(80.dp)
                                )
                                Text(
                                    text = lazyPokemon[index]?.name ?: String.empty(),
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
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
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = {  },
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
fun FilterBottomSheetPreview(){
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
    FilterOption(Type(
        name = "Fire",
        enabled = true
    ))
}
