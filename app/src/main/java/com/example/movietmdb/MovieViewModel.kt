package com.example.movietmdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movietmdb.placeholder.PlaceholderContent

class MovieViewModel : ViewModel() {

    // Responsible for passing data and listening to layout data
    val movieDetailsLiveData: LiveData<MovieDetails>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<MovieDetails>()

    val movieListLiveData: LiveData<MutableList<PlaceholderContent.PlaceholderItem>>
        get() = _movieListLiveData
    private val _movieListLiveData =
        MutableLiveData<MutableList<PlaceholderContent.PlaceholderItem>>()

    val navigationToDetailLiveData
        get() = _navigationToDetailLiveData
    private val _navigationToDetailLiveData = MutableLiveData<Unit>()

    init {
        _movieListLiveData.postValue(PlaceholderContent.ITEMS)
    }

    fun onMovieSelected(position: Int) {
        val movieDetails = MovieDetails("Test Title", "Test Content")
        _movieDetailsLiveData.postValue(movieDetails)
        _navigationToDetailLiveData.postValue(Unit)
    }

}