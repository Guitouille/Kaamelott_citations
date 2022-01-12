package com.example.kaamelottcitations.data.kaamelottquotes.datasource.remote

import com.google.gson.annotations.SerializedName

data class QuotesRemoteEntity(
    val status: Int = -1,
    @SerializedName("citation")
    val quotes: List<QuoteEntity> = emptyList()
)

data class QuoteEntity(
    @SerializedName("citation")
    val quote: String,
    @SerializedName("infos")
    val details: DetailsEntity
)

data class DetailsEntity(
    @SerializedName("acteur")
    val actor: String,
    @SerializedName("personnage")
    val character: String,
    @SerializedName("auteur")
    val author: String,
    @SerializedName("saison")
    val season: String,
    val episode: String
)
