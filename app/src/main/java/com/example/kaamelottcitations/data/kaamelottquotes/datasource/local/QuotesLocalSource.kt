package com.example.kaamelottcitations.data.kaamelottquotes.datasource.local

import javax.inject.Inject

class QuotesLocalSource @Inject constructor(private val database: QuotesDatabase) {

    fun fetchCharacterQuotesByBook(
        book: Int,
        character: String
    ): String? {
        val quotesDao = database.quotesDao()
        return quotesDao.findByBookAndCharacter(book = book, character = character)
    }

    fun insertQuotes(
        book: Int,
        character: String,
        quotes: String
    ) : Unit {
        database.quotesDao().insertQuote(
            QuotesLocalEntity(book, character, quotes)
        )
    }
}