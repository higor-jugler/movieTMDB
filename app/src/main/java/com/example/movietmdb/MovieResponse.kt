package com.example.movietmdb

import com.example.movietmdb.data.Movie
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    val results: List<Movie>?
)