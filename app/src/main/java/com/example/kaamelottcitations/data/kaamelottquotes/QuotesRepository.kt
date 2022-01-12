package com.example.kaamelottcitations.data.kaamelottquotes

import com.example.kaamelottcitations.data.kaamelottquotes.datasource.local.QuotesLocalSource
import com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote.QuotesRemoteEntity
import com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote.QuotesRemoteSource
import com.example.kaamelottcitations.ui.quotes.KaamelottQuotes
import com.google.gson.Gson
import javax.inject.Inject

class QuotesRepository @Inject constructor(
    private val quotesRemoteSource: QuotesRemoteSource,
    private val quotesLocalSource: QuotesLocalSource,
    private val gson: Gson
) : KaamelottQuotes {

    override suspend fun fetchCharacterQuotesByBook(
        book: Int,
        character: String
    ): Result<QuotesRemoteEntity> {

        val localQuotes: String? = quotesLocalSource.fetchCharacterQuotesByBook(book, character)

        if (localQuotes.isNullOrEmpty()) {
            val remoteQuote = quotesRemoteSource.fetchCharacterQuotesByBook(book, character)
            quotesLocalSource.insertQuotes(book, character, gson.toJson(remoteQuote))
            return Result.success(remoteQuote)
        }

        return Result.success(gson.fromJson(localQuotes, QuotesRemoteEntity::class.java))
    }
}