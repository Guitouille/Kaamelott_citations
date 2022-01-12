package com.example.kaamelottcitations.ui.quotes

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kaamelottcitations.R

@Composable
fun CharacterQuotesByBookScreen(
    characterQuotesViewModel: CharacterQuotesViewModel = hiltViewModel(),
    bookNumber: Int,
    characterNameIndex: Int
) {

    val uiState = characterQuotesViewModel.uiState

    Crossfade(targetState = uiState.loading) { state ->
        if (state) {
            characterQuotesViewModel.fetchCharacterQuotesByBook(
                bookNumber = bookNumber,
                characterName = stringArrayResource(id = R.array.characters)[characterNameIndex]
            )
            ProgressIndicator()
        } else {
            if (uiState.quotes.quote.isEmpty()) {
                NoQuotes()
            } else {
                QuoteList(quotesModel = uiState.quotes)
            }
        }
    }
}

@Composable
fun ProgressIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Text("Chargement en cours...")
    }
}

@Preview(widthDp = 200, heightDp = 100)
@Composable
fun ProgressIndicatorPreview() {
    ProgressIndicator()
}

@Composable
fun NoQuotes() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Pas de citations, désolé")
    }
}

@Preview(widthDp = 200, heightDp = 100)
@Composable
fun NoQuotesPreview() {
    NoQuotes()
}

@Composable
fun QuoteList(quotesModel: QuotesModel) {
    LazyColumn {
        items(quotesModel.quote.size) {
            QuoteItem(quote = quotesModel.quote[it])
        }
    }
}

@Composable
fun QuoteItem(quote: Quote) {
    Column(
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Text(text = quote.text)
        Text(text = quote.metadata.actor)
        Text(text = quote.metadata.author)
        Text(text = quote.metadata.character)
        Text(text = quote.metadata.episode)
        Text(text = quote.metadata.season)
    }
}

@Preview
@Composable
fun QuoteItemPreview() {
    QuoteItem(
        quote = Quote(
            text = "Texte de la citation",
            MetaData(
                actor = "actor",
                author = "author",
                character = "character",
                episode = "episode",
                season = "season"
            )
        )
    )
}
