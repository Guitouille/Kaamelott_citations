package com.example.kaamelottcitations.ui.quotes

import com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote.KaamelottQuotesEntity

interface KaamelottQuotes {

    suspend fun fetchCharacterQuotesByBook(
        book: Int,
        character: String
    ): Result<KaamelottQuotesEntity>
}