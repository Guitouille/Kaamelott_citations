package com.example.kaamelottcitations.data.kaamelottquotes.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["book", "character"])
data class QuotesLocalEntity(
    @ColumnInfo(name = "book") val book: Int,
    @ColumnInfo(name = "character") val character: String,
    @ColumnInfo(name = "quotes") val quotes: String?
)
