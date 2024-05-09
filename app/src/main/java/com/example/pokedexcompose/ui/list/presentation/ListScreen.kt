package com.example.pokedexcompose.ui.list.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.pokedexcompose.extensions.empty
import com.pokedexcompose.designsystem.components.loading.Lottie

@RequiresApi(Build.VERSION_CODES.P)
@Composable
internal fun PokemonListScreen(
    uiState: PokemonListUiState,
    modifier: Modifier = Modifier
) {
    if (uiState.loading) {
        Lottie(url = SHIMMER_LOTTIE_JSON)
    } else {

        val list = listOf("agua", "fogo", "ar", "terra", "eletricidade", "pedra", "magicos")

        Column {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                items(list) {
                    Box(
                        modifier = Modifier
                            .widthIn(60.dp)
                            .clip((RoundedCornerShape(15.dp)))
                            .background(Color.Blue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it,
                            Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            val lazyPokemon: LazyPagingItems<ResultListDomain> =
                uiState.result.collectAsLazyPagingItems()
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

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
private fun PokemonListScreenPreview() {
    PokemonListScreen(PokemonListUiState(

    ))
}