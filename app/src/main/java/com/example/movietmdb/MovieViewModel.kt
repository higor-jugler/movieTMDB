package com.example.movietmdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietmdb.api.MovieService
import com.example.movietmdb.data.ApiCredentials
import com.example.movietmdb.data.Event
import com.example.movietmdb.data.Movie
import com.example.movietmdb.helpers.ImageUtil
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieViewModel : ViewModel() {

    // Responsible for passing data and listening to layout data
    val movieDetailsLiveData: LiveData<Movie>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<Movie>()

    val viewDataStateLiveData: LiveData<ViewDataState>
        get() = _viewDataStateLiveData
    private val _viewDataStateLiveData = MutableLiveData<ViewDataState>()

    val movieListLiveData: LiveData<List<Movie>?>
        get() = _movieListLiveData
    private val _movieListLiveData = MutableLiveData<List<Movie>?>()

    val carouselImagesLiveData: LiveData<List<CarouselItem>?>
        get() = _carouselImagesLiveData
    private val _carouselImagesLiveData = MutableLiveData<List<CarouselItem>?>(null)

    val navigationToDetailLiveData
        get() = _navigationToDetailLiveData
    private val _navigationToDetailLiveData = MutableLiveData<Event<Unit>>()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials().baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val movieService = retrofit.create(MovieService::class.java)

    init {
        _viewDataStateLiveData.postValue(ViewDataState.LOADING)
        getMovieData()
    }

    fun onMovieSelected(position: Int) {
        val movieDetails = _movieListLiveData.value?.getOrNull(position)

        movieDetails?.let {
            viewModelScope.launch {
                _movieDetailsLiveData.postValue(it)
                _navigationToDetailLiveData.postValue(Event<Unit>(Unit))

                // Loading image from movie selected
                val carouselImages =
                    it.id?.let { it1 -> ImageUtil.getCarouselImages(it1, movieService) }
                _carouselImagesLiveData.postValue(carouselImages)
            }
        }
    }

    fun getVoteAverageString(): String {
        return movieDetailsLiveData.value?.voteAverage?.toString() ?: ""
    }

    private fun getMovieData() {
        val apiKey = ApiCredentials().apiKey
        val page = 1

        // Call API
        viewModelScope.launch {
            val response = movieService.getLatestMovies(apiKey, page)

            if (response.isSuccessful) {
                _movieListLiveData.postValue(response.body()?.results)
                _viewDataStateLiveData.postValue(ViewDataState.SUCCESS)

            } else {
                _viewDataStateLiveData.postValue(ViewDataState.ERROR)
            }
        }
    }
}