package com.example.kaamelottcitations.core.di

import android.content.Context
import androidx.room.Room
import com.example.kaamelottcitations.data.kaamelottquotes.datasource.local.QuotesDatabase
import com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote.QuotesService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient
            .Builder()
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://kaamelott.chaudie.re")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideKaamelottQuotesService(retrofit: Retrofit): QuotesService =
        retrofit.create(QuotesService::class.java)

    @Provides
    @Singleton
    fun provideQuotesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            QuotesDatabase::class.java,
            "quotes-database"
        ).build()

    @Provides
    @Singleton
    fun provideGson() = Gson()
}
