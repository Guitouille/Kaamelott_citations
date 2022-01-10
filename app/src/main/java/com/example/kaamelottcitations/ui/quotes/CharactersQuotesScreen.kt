package com.example.kaamelottcitations.ui.quotes

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CharacterQuotesByBookScreen(bookNumber: Int, characterNameIndex: Int) {
    Text("Hello $bookNumber and $characterNameIndex")
}