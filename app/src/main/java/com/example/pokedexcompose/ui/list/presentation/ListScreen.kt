package com.example.pokedexcompose.ui.list.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
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

@RequiresApi(Build.VERSION_CODES.P)
@Composable
internal fun PokemonListScreen(uiState: PokemonListUiState) {
    if (uiState.loading) {
        LayoutShimmer()
    } else {

        val lazyCharacters: LazyPagingItems<ResultListDomain> = uiState.result.collectAsLazyPagingItems()

        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(lazyCharacters.itemCount) { index ->
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
                                    .data(lazyCharacters[index]?.gif ?: "")
                                    .decoderFactory(ImageDecoderDecoder.Factory()).build(),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(80.dp)
                            )
                            Text(
                                text = lazyCharacters[index]?.name ?: "",
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

@Composable
fun ShimmerBrush(showShimmer: Boolean = true, targetValue: Float = 1000f) {
    val brush = if (showShimmer) {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f)
        )

        val transition = rememberInfiniteTransition()
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800),
                repeatMode = RepeatMode.Reverse
            )
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
    Column(
        modifier = Modifier
            .width(128.dp)
            .height(200.dp)
            .padding(4.dp)
    ) {
        Spacer(
            modifier = Modifier
                .background(brush)
                .fillMaxWidth()
                .height(200.dp)
                .padding(4.dp)
        )
    }
}

@Composable
fun LayoutShimmer() {
    val amountShimmer = 10
    LazyVerticalGrid(columns = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(amountShimmer) { index ->
                ShimmerBrush(true, 1300f)
            }
        })
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ShimmerBrushPreview() {
    ShimmerBrush()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LayoutShimmerPreview() {
    LayoutShimmer()
}
