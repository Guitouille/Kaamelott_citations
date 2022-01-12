package com.example.kaamelottcitations.data.kaamelottquotes.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuotesDao {

    @Insert
    fun insertQuote(quotesLocalEntity: QuotesLocalEntity)

    @Query("SELECT quotes FROM QuotesLocalEntity WHERE book = :book AND " +
                 "character LIKE :character")
    fun findByBookAndCharacter(book: Int, character: String): String?
}