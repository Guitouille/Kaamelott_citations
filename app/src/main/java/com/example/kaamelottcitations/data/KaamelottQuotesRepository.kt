package com.example.kaamelottcitations.data

import javax.inject.Inject

class KaamelottQuotesRepository @Inject constructor(private val kaamelottQuotesService: KaamelottQuotesService) {

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