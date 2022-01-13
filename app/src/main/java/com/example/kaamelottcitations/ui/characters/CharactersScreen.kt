package com.example.kaamelottcitations.ui.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kaamelottcitations.R
import com.example.kaamelottcitations.ui.Toolbar
import com.example.kaamelottcitations.ui.theme.QuickSand
import com.example.kaamelottcitations.ui.theme.RomanCoffee

@Composable
fun CharacterList(bookNumber: Int, onCharacterClicked: (Int, Int) -> Unit) {
    val charactersArray = stringArrayResource(id = R.array.characters)
    Column(
        modifier = Modifier
            .background(color = RomanCoffee)
    ) {
        Toolbar(title = stringResource(R.string.screen_title_characters))
        LazyColumn {
            items(charactersArray.size) { index ->
                CharacterRow(charactersArray[index], index, bookNumber, onCharacterClicked)
            }

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
    Card(
        modifier = Modifier
            .clickable { onCharacterClicked(bookNumber, nameIndex) }
            .padding(all = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(color = QuickSand)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                text = name,
                color = Color.White
            )
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}

@Preview
@Composable
fun CharacterRowPreview() {
    CharacterRow("Arthur", 1, 1) { _, _ -> }
}
