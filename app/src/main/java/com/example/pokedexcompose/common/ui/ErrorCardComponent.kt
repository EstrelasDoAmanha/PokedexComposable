package com.example.pokedexcompose.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedexcompose.R

@Composable
private fun ErrorCard(
    title: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.ErrorOutline,
    iconColor: Color = MaterialTheme.colorScheme.error,
    iconDescription: String = stringResource(R.string.content_desc_alert)
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 128.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .clip(
                    shape = RoundedCornerShape(16.dp)
                )
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = iconDescription,
                tint = iconColor,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            OutlinedButton(
                onClick = {
                    onButtonClick()
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp)
            ) {
                Text(text = buttonText)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorCardPreview() {
    ErrorCard(
        title = "Algo deu erro",
        buttonText = "Tentar novamente",
        onButtonClick = {}
    )
}
