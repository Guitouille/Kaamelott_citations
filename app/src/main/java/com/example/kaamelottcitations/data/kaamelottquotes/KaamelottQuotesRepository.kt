package com.example.kaamelottcitations.data.kaamelottquotes

import com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote.KaamelottQuotesEntity
import com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote.KaamelottQuotesRemoteSource
import com.example.kaamelottcitations.ui.quotes.KaamelottQuotes
import javax.inject.Inject

class KaamelottQuotesRepository @Inject constructor(private val kaamelottQuotesRemoteSource: KaamelottQuotesRemoteSource) :
    KaamelottQuotes {

    override suspend fun fetchCharacterQuotesByBook(
        book: Int,
        character: String
    ): Result<KaamelottQuotesEntity> {
        return kaamelottQuotesRemoteSource.fetchCharacterQuotesByBook(book, character)
    }
}