package com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuotesService {

    @GET("/api/all/livre/{book}/personnage/{character}")
    suspend fun getCharacterQuoteByBook(
        @Path("book") book: Int,
        @Path("character") character: String
    ): Response<QuotesRemoteEntity>
}