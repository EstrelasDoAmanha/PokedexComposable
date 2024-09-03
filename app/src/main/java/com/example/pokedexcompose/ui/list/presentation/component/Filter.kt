package com.example.pokedexcompose.ui.list.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedexcompose.domain.model.Type

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
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        FilterList(type, query, onDismiss)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterList(type: List<Type>, query: (String) -> Unit, onDismiss: () -> Unit) {
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
fun FilterOption(tag: Type, onClick: () -> Unit = {}) {
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
