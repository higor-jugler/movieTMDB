package com.example.movietmdb.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    val id: Int?,
    val title: String?,
    val overview: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "vote_average") val voteAverage: Double?
) {
    fun getIdString(): String {
        return id?.toString() ?: ""
    }

    fun getImageUrl(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }
}
