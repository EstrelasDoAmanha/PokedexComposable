package com.example.pokedexcompose.ui.list.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.pokedexcompose.domain.model.ResultListDomain
import com.example.pokedexcompose.extensions.empty

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PokemonItem(
    index: Int,
    onItemClick: (String) -> Unit = {},
    lazyPokemon: LazyPagingItems<ResultListDomain>
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onItemClick(
                    index
                        .inc()
                        .toString()
                )
            }
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