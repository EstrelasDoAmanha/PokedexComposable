package com.example.pokedexcompose.ui.details.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.PokemonStatistics
import com.example.pokedexcompose.domain.model.PokemonType
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PokemonDetailScreen(uiState: PokemonDetailsUiState, onRetryClick: () -> Unit = {}) {
    when {
        uiState.isLoading -> LoadingCardContent()
        uiState.isError -> ErrorCard(onRetryClick)
        else -> DetailsMainContent(uiState)
    }
}

@Composable
private fun LoadingCardContent() {
    Card(
        modifier = Modifier.fillMaxSize(0.7f)
    ) {
        Text(text = "Carregando")
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
private fun DetailsMainContent(uiState: PokemonDetailsUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = getPokemonBgColor(uiState.pokemonInfo.type))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .clip(
//                    shape = RoundedCornerShape(
//                        bottomStart = 25.dp,
//                        bottomEnd = 25.dp
//                    )
//                )
        ) {
            AsyncImage(
                alignment = Alignment.Center,
                contentScale = ContentScale.Inside,
                // contentScale = ContentScale.Fit,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getGifUrl(uiState.pokemonInfo.id))
                    .decoderFactory(ImageDecoderDecoder.Factory()).build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Text(
                text = uiState.pokemonInfo.name.capitalize(),
                fontWeight = FontWeight.Black,
                fontSize = 28.sp,
                textAlign = TextAlign.Start,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Text(
                text = uiState.pokemonInfo.id.toString(),
                fontWeight = FontWeight.Black,
                fontSize = 28.sp,
                textAlign = TextAlign.End,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(uiState.pokemonInfo.type) {
                    Card(
                        colors = CardColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White,
                            disabledContainerColor = Color.LightGray,
                            disabledContentColor = Color.White
                        ),
                        modifier = Modifier
                            .background(
                                color = Color.White.copy(alpha = 0.3f),
                                shape = ShapeDefaults.Small
                            )
                            .padding(horizontal = 2.dp)
                    ) {
                        Text(
                            text = it.name.capitalize(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 2.dp)

                        )
                    }
                }
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .heightIn(min = 150.dp, max = 300.dp)
                .align(Alignment.CenterHorizontally)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 25.dp,
                        topEnd = 25.dp
                    )
                )
                .background(MaterialTheme.colorScheme.surface)

        ) {
            items(uiState.pokemonInfo.stats) { stat ->
                PokemonAttributeComp(
                    stat
                )
            }
        }
        // AttributesComp(uiState)
    }
}

@Composable
private fun ErrorCard(onRetryClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)

    ) {
        Icon(
            imageVector = Icons.Outlined.Warning,
            contentDescription = "Alerta",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Text(
            text = "ERROU",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f)

        )
        Button(
            onClick = {
                onRetryClick()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp)
        ) {
            Text(text = "Tentar novamente")
        }
    }
}

@Composable
private fun getPokemonBgColor(type: List<PokemonType>): Color {
    return type.firstOrNull()?.let {
        TypeColor.parse(it.name).color
    } ?: TypeColor.NORMAL.color
}

@Composable
private fun AttributesComp(uiState: PokemonDetailsUiState) {
    Card(
        modifier = Modifier
            .size(60.dp)
            .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 1f))
            .border(width = 2.dp, Color.DarkGray)

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.Build,
                contentDescription = "Peso",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)

            )
            Text(text = uiState.pokemonInfo.weight.toString())
        }
    }
}

@Composable
fun PokemonAttributeComp(stat: PokemonStatistics) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stat.name,
            color = MaterialTheme.colorScheme.secondary,
            modifier =
            Modifier.width(90.dp),
            textAlign = TextAlign.Start
        )

        Text(
            text = stat.baseStat.toString(),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .width(60.dp)
                .align(alignment = Alignment.CenterVertically),
            textAlign = TextAlign.Center
        )

        LinearProgressIndicator(
            progress = { stat.baseStat / 252f },
            color = getStatsColor(stat),
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Round,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
    }
}

@Composable
private fun getStatsColor(stat: PokemonStatistics) =
    (if (stat.baseStat < 60) Color.Red else Color.Green).copy(alpha = 0.4f)

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun PokemonDetailScreenPreview() {
    PokemonDetailScreen(getUiState())
}

fun getUiState() = PokemonDetailsUiState(
    isLoading = false,
    isError = false,
    pokemonInfo = PokemonInfo(
        name = "Pikachu",
        image = "",
        height = 4,
        id = Random.nextInt(0, 700),
        order = 25,
        weight = 60,
        stats = getStatsList(),
        type = getTypes()
    )
)

fun getTypes(): List<PokemonType> {
    return listOf(
        PokemonType(name = "eletric"),
        PokemonType(name = "Normal")
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

fun getGifUrl(id: Int): String {
//    return "https://raw.githubusercontent.com/PokeAPI/" +
//            "sprites/master/sprites/pokemon/other/showdown/$id.gif"
    return "https://raw.githubusercontent.com/PokeAPI/" +
        "sprites/master/sprites/pokemon/other/home/$id.png"
}
