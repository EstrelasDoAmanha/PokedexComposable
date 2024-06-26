package com.example.pokedexcompose.ui.details.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Expand
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.pokedexcompose.R
import com.example.pokedexcompose.common.SHIMMER_LOTTIE_JSON
import com.example.pokedexcompose.common.getPokemonHomeSpriteUrl
import com.example.pokedexcompose.common.ui.ErrorScreenComponent
import com.example.pokedexcompose.domain.model.PokemonInfo
import com.example.pokedexcompose.domain.model.PokemonStatistics
import com.example.pokedexcompose.domain.model.PokemonType
import com.pokedexcompose.designsystem.components.loading.Lottie
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PokemonDetailScreen(
    uiState: PokemonDetailsUiState,
    updateTopBarColor: (Color) -> Unit,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit = {}
) {
    when {
        uiState.isLoading -> LoadingCardContent()
        uiState.isError -> ErrorScreenComponent(
            title = stringResource(R.string.details_error_title),
            body = stringResource(R.string.details_error_message),
            primaryButtonText = stringResource(R.string.try_again_button),
            onPrimaryButtonClick = onRetryClick,
            modifier = modifier
        )

        else -> DetailsMainContent(uiState, modifier, updateTopBarColor)
    }
}

@Composable
private fun LoadingCardContent(modifier: Modifier = Modifier) {
    Lottie(url = SHIMMER_LOTTIE_JSON, modifier)
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
private fun DetailsMainContent(
    uiState: PokemonDetailsUiState,
    modifier: Modifier = Modifier,
    updateTopBarColor: (Color) -> Unit
) {
    val backgroundColor by remember {
        mutableStateOf(getPokemonBgColor(uiState.pokemonInfo.type))
    }
    updateTopBarColor(backgroundColor)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .verticalScroll(rememberScrollState(), enabled = true)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = uiState.pokemonInfo.id.toString(),
                fontWeight = FontWeight.Black,
                fontSize = 120.sp,
                textAlign = TextAlign.End,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 16.dp)
                    .alpha(0.5f)

            )

            AsyncImage(
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.Inside,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getPokemonHomeSpriteUrl(uiState.pokemonInfo.id))
                    .decoderFactory(ImageDecoderDecoder.Factory()).build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 25.dp,
                        topEnd = 25.dp
                    )
                )
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            LazyRow(
                contentPadding = PaddingValues(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(
                    16.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)

            ) {
                items(uiState.pokemonInfo.type) {
                    PokemonTypeComponent(
                        it.name,
                        applyIconColorFilter = false
                    )
                }
            }

            Text(
                text = "Estatisticas",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

            )

            LazyColumn(
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .heightIn(max = 600.dp)

            ) {
                items(uiState.pokemonInfo.stats) { stat ->
                    PokemonAttributeComp(
                        stat
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                PokemonInfoComp(
                    icon = Icons.Filled.Balance,
                    iconDescription = "Peso",
                    getFormattedWeightInfo(uiState.pokemonInfo.weight)
                )

                PokemonInfoComp(
                    icon = Icons.Filled.Expand,
                    iconDescription = "Altura",
                    getFormattedHeightInfo(uiState.pokemonInfo.height)
                )
            }
        }
    }
}

@Composable
private fun PokemonTypeComponent(
    type: String,
    modifier: Modifier = Modifier,
    applyIconColorFilter: Boolean = false,
    inverseColor: Color = Color.White
) {
    val typeUrl = getPokemonTypeUrl(type)
    val typeColor = getPokemonTypeColor(type = type)
    val backgroundColor = if (applyIconColorFilter) inverseColor else typeColor
    val filterColor = if (applyIconColorFilter) typeColor else inverseColor

    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(color = backgroundColor)
            .border(
                width = 1.dp,
                color = filterColor,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(2.dp)
            .border(
                width = 1.dp,
                color = filterColor,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(8.dp)

    ) {
        AsyncImage(
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            model = ImageRequest.Builder(LocalContext.current)
                .data(typeUrl)
                .decoderFactory(SvgDecoder.Factory()).build(),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = filterColor,
                blendMode = BlendMode.SrcAtop
            )
        )
    }
}

@Composable
fun getFormattedHeightInfo(height: Int) = stringResource(R.string.height_in_centimeter, height * 10)

@Composable
fun getFormattedWeightInfo(weight: Int) = stringResource(R.string.weight_in_kilo, weight / 10)

private fun getPokemonBgColor(type: List<PokemonType>): Color {
    return type.firstOrNull()?.let {
        TypeColor.parse(it.name).color
    } ?: TypeColor.NORMAL.color
}

private fun getPokemonTypeColor(type: String): Color {
    return TypeColor.parse(type).color
}

@Composable
private fun PokemonInfoComp(icon: ImageVector, iconDescription: String, infoText: String) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .height(90.dp)
            .clip(
                shape = RoundedCornerShape(16.dp)
            )
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(4.dp)

    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        )
        Text(
            text = infoText
        )
    }
}

@Composable
fun PokemonAttributeComp(stat: PokemonStatistics) {
    val maxStatsValue by remember { mutableFloatStateOf(252f) }
    val animDuration by remember { mutableIntStateOf(1500) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        var animationPlayed by remember {
            mutableStateOf(false)
        }
        val percent = stat.baseStat / maxStatsValue
        val currentPercent = animateFloatAsState(
            label = stringResource(R.string.pokemon_stats_animation_label),
            targetValue = if (animationPlayed) percent else 0f,
            animationSpec = tween(
                durationMillis = animDuration,
                delayMillis = 0
            )
        )

        LaunchedEffect(key1 = true) {
            animationPlayed = true
        }

        Text(
            text = stat.name.uppercase(),
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
            progress = { currentPercent.value },
            color = getStatsColor(stat),
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Round,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
    }
}

private fun getStatsColor(stat: PokemonStatistics) =
    (if (stat.baseStat < 60) Color.Red else Color.Green).copy(alpha = 0.4f)

fun getPokemonTypeUrl(type: String): String {
    return "https://raw.githubusercontent.com/" +
        "duiker101/pokemon-type-svg-icons/master/icons/$type.svg"
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun PokemonDetailScreenPreview() {
    PokemonDetailScreen(getUiState(), updateTopBarColor = {}) {}
}

@Preview(showBackground = true)
@Composable
fun PokemonIconTypeComponentPreview() {
    PokemonTypeComponent(type = "Grass")
}

@Preview(showBackground = true)
@Composable
fun PokemonInfoCompPreview() {
    PokemonInfoComp(
        icon = Icons.Filled.Balance,
        iconDescription = "peso",
        infoText = "6000Kg"
    )
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
