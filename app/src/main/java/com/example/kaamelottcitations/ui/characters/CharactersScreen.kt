package com.example.kaamelottcitations.ui.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kaamelottcitations.R

@Composable
fun CharacterList(bookNumber: Int, onCharacterClicked: (Int, Int) -> Unit) {
    val charactersArray = stringArrayResource(id = R.array.characters)
    LazyColumn {
        items(charactersArray.size) { index ->
            CharacterRow(charactersArray[index], index, bookNumber, onCharacterClicked)
            Divider(color = Color.DarkGray)
        }

    }
}

@Composable
fun CharacterRow(
    name: String,
    nameIndex: Int,
    bookNumber: Int,
    onCharacterClicked: (Int, Int) -> Unit
) {
    Row(
        modifier = Modifier.clickable { onCharacterClicked(bookNumber, nameIndex) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            text = name
        )
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            null
        )
    }
}

@Preview
@Composable
fun CharacterRowPreview() {
    CharacterRow("Arthur", 1, 1) { _, _ -> }
}
