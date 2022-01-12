package com.example.kaamelottcitations.data.kaamelottquotes.datasource.local

import com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote.KaamelottQuotesEntity
import com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote.KaamelottQuotesService
import javax.inject.Inject

class KaamelottQuotesLocalSource @Inject constructor(private val kaamelottQuotesService: KaamelottQuotesService) {

    suspend fun fetchCharacterQuotesByBook(
        book: Int,
        character: String
    ): Result<KaamelottQuotesEntity> {
        var quotesList = KaamelottQuotesEntity()
        val result = kaamelottQuotesService.getCharacterQuoteByBook(book, character)

        if (result.isSuccessful) {
            quotesList = result.body() as KaamelottQuotesEntity
        }
        return Result.success(quotesList)
    }
}