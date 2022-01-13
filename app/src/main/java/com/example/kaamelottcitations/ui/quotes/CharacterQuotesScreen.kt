package com.example.kaamelottcitations.ui.quotes

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kaamelottcitations.R
import com.example.kaamelottcitations.ui.Toolbar
import com.example.kaamelottcitations.ui.theme.RomanCoffee
import com.example.kaamelottcitations.ui.theme.VanillaIce

@Composable
fun CharacterQuotesByBookScreen(
    characterQuotesViewModel: CharacterQuotesViewModel = hiltViewModel(),
    bookNumber: Int,
    characterNameIndex: Int
) {

    Column(
        modifier = Modifier
            .background(color = RomanCoffee)
            .fillMaxHeight()
    ) {
        Toolbar(title = stringResource(R.string.screen_title_quotes))
        //TODO: Search for better state transition
        when (val uiState = characterQuotesViewModel.uiState.value) {
            is CharacterQuotesViewModel.UiState.Loading -> LoadingState(
                characterQuotesViewModel = characterQuotesViewModel,
                bookNumber = bookNumber,
                characterNameIndex = characterNameIndex
            )
            is CharacterQuotesViewModel.UiState.Success -> SuccessState(uiState.quotes)
            is CharacterQuotesViewModel.UiState.Error -> null //TODO: Error state
        }
    }
}

@Composable
fun LoadingState(
    characterQuotesViewModel: CharacterQuotesViewModel,
    bookNumber: Int,
    characterNameIndex: Int
) {
    characterQuotesViewModel.fetchCharacterQuotesByBook(
        bookNumber = bookNumber,
        characterName = stringArrayResource(id = R.array.characters)[characterNameIndex]
    )
    ProgressIndicator()
}

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun SuccessState(quotesModel: QuotesModel) {
    Crossfade(targetState = CharacterQuotesViewModel.UiState.Loading) {
        //TODO: Extract EmptyState
        if (quotesModel.quote.isEmpty()) {
            NoQuotes()
        } else {
            QuoteList(quotesModel = quotesModel)
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
        CircularProgressIndicator(color = Color.White)
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.loading),
            style = MaterialTheme.typography.h6,
            color = Color.White
        )
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
        Icon(
            modifier = Modifier.size(70.dp),
            imageVector = Icons.Filled.ErrorOutline,
            contentDescription = null,
            tint = Color.White,
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.no_quotes),
            style = MaterialTheme.typography.h6,
            color = Color.White
        )
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
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = VanillaIce
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = quote.text.trim(),
            )
        }
        QuoteMeta(
            text = stringResource(id = R.string.actor, quote.metadata.actor) + " | " +
                    stringResource(id = R.string.author, quote.metadata.author) + " | " +
                    stringResource(id = R.string.author, quote.metadata.character) + " | " +
                    stringResource(id = R.string.character, quote.metadata.episode) + " | " +
                    stringResource(id = R.string.character, quote.metadata.season) + " | "
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun QuoteMeta(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2
    )
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
