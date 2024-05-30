package com.example.pokedexcompose.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedexcompose.common.getPokemonHomeSpriteUrl
import com.example.pokedexcompose.extensions.empty

@Composable
fun ErrorScreenComponent(
    title: String,
    body: String,
    primaryButtonText: String,
    onPrimaryButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconTintColor: Color = MaterialTheme.colorScheme.error,
    secondButtonText: String = String.empty(),
    onSecondButtonClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "image",
                    tint = iconTintColor,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.4f)
                        .padding(
                            8.dp
                        )
                )
            } ?: run {
                AsyncImage(
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Inside,
                    model = getPokemonHomeSpriteUrl(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.4f)
                        .padding(
                            8.dp
                        )
                )
            }

            Text(
                textAlign = TextAlign.Center,
                text = title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Text(
                textAlign = TextAlign.Center,
                text = body,
                fontSize = 20.sp,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 72.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            onPrimaryButtonClick()
                        }
                    ) {
                        Text(
                            text = primaryButtonText,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                    if (secondButtonText.isNotEmpty()) {
                        Button(
                            onClick = {
                                onSecondButtonClick()
                            }
                        ) {
                            Text(
                                text = secondButtonText,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenComponentPreview() {
    ErrorScreenComponent(
        title = "Ops!",
        body = "Não encontramos o que buscavamos!\nQue tal tentar novamente?",
        icon = Icons.Outlined.ErrorOutline,
        iconTintColor = MaterialTheme.colorScheme.error,
        primaryButtonText = "Tentar Novamente",
        onPrimaryButtonClick = {}
    )
}
