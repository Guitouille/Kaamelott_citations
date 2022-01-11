package com.example.kaamelottcitations.core.di

import com.example.kaamelottcitations.data.KaamelottQuotesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideKaamelottQuotesService(retrofit: Retrofit) =
        retrofit.create(KaamelottQuotesService::class.java)

}
