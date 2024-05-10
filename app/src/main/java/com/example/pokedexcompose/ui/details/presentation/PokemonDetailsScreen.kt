package com.example.pokedexcompose.ui.details.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.PokemonStatistics
import com.example.pokedexcompose.domain.model.PokemonType

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PokemonDetailScreen(uiState: PokemonDetailsUiState) {
    if (uiState.isLoading) {
        Card(
            modifier = Modifier.fillMaxSize(0.7f)
        ) {
            Text(text = "Carregando")
        }
    } else {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 100.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(getGifUrl(uiState.pokemonInfo.id))
                        .decoderFactory(ImageDecoderDecoder.Factory()).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp)
                        .align(Alignment.Center)
                )
            }
            LazyHorizontalGrid(
                horizontalArrangement = Arrangement.Center,
                rows = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 150.dp, max = 300.dp)

            ) {
                items(uiState.pokemonInfo.stats) { stat ->
                    PokemonAttributeComp(
                        stat
                    )
                }
            }

//    PokemonAttributeComp(
//        PokemonStatistics("ronaldo",250)
//    )
        }
    }
}

@Composable
fun PokemonAttributeComp(stat: PokemonStatistics) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(125.dp)
            .height(125.dp)

    ) {
        CircularProgressIndicator(
            progress = {
                stat.baseStat / 252f
            },
            color = Color.Green,
            strokeWidth = 10.dp,
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .fillMaxSize()
                .rotate(180f)

        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            Text(
                text = stat.baseStat.toString(),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Text(
                text = stat.name,
                fontSize = 12.sp,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(alpha = 0.85f),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(horizontal = 4.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun PokemonDetailScreenPreview() {
    PokemonDetailScreen(getUiState())
}

fun getUiState() = PokemonDetailsUiState(
    isLoading = false,
    pokemonInfo = PokemonInfo(
        name = "Pikachu",
        image = "",
        height = 4,
        id = 25,
        order = 25,
        weight = 60,
        stats = getStatsList(),
        type = getTypes()
    )
)

fun getTypes(): List<PokemonType> {
    return listOf(
        PokemonType(name = "eletric")
    )
}

fun getStatsList(): List<PokemonStatistics> {
    return listOf(
        PokemonStatistics(
            name = "Hp",
            baseStat = 35
        ),
        PokemonStatistics(
            name = "Speed",
            baseStat = 90
        ),
        PokemonStatistics(
            name = "Spc Defense",
            baseStat = 50
        ),
        PokemonStatistics(
            name = "Spc Attack",
            baseStat = 50
        ),
        PokemonStatistics(
            name = "Defense",
            baseStat = 40
        ),
        PokemonStatistics(
            name = "Attack",
            baseStat = 55
        )
    )
}

fun getGifUrl(id: Int): String  {
    return "https://raw.githubusercontent.com/PokeAPI/" +
        "sprites/master/sprites/pokemon/other/showdown/shiny/$id.gif"
}
