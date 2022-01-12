package com.example.kaamelottcitations.ui.quotes

import com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote.KaamelottQuotesEntity

data class QuotesModel(
    val quote: List<Quote> = emptyList(),
)

data class Quote(
    val text: String,
    val metadata: MetaData
)

data class MetaData(
    val actor: String,
    val character: String,
    val author: String,
    val season: String,
    val episode: String
)

fun KaamelottQuotesEntity.toQuotesModel(): QuotesModel {
    return QuotesModel(
        this.quotes.map {
            Quote(
                text = it.quote,
                metadata = MetaData(
                    actor = it.details.actor,
                    character = it.details.character,
                    author = it.details.author,
                    season = it.details.season,
                    episode = it.details.episode
                )
            )
        }
    )
}