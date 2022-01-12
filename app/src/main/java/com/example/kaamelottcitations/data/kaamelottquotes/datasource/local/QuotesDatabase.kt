package com.example.kaamelottcitations.data.kaamelottquotes.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuotesLocalEntity::class], version = 1)
abstract class QuotesDatabase : RoomDatabase(){
    abstract fun quotesDao(): QuotesDao
}