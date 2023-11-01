package com.example.movietmdb

import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {

    fun loadMovieDetailsTest() : MovieDetails{
        return MovieDetails("Test Title", "Test Content")
    }

}