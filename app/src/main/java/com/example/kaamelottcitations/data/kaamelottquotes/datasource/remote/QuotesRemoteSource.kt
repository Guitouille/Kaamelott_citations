package com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote

import javax.inject.Inject

class QuotesRemoteSource @Inject constructor(private val quotesService: QuotesService) {

    suspend fun fetchCharacterQuotesByBook(
        book: Int,
        character: String
    ): QuotesRemoteEntity {
        var quotesList = QuotesRemoteEntity()
        val result = quotesService.getCharacterQuoteByBook(book, character)

        if (result.isSuccessful) {
            quotesList = result.body() as QuotesRemoteEntity
        }
        return quotesList
    }
}