package com.example.movietmdb.helpers

import com.example.movietmdb.api.MovieService
import com.example.movietmdb.data.ApiCredentials
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

object ImageUtil {
    suspend fun getCarouselImages(movieId: Int, movieService: MovieService): List<CarouselItem> {
        val apiKey = ApiCredentials().apiKey
        val response = movieService.getMovieImages(movieId, apiKey)

        if (response.isSuccessful) {
            val imagesResponse = response.body()
            val backdropImages = imagesResponse?.backdrops?.take(10)?.mapNotNull { backdrop ->
                val imageUrl = "https://image.tmdb.org/t/p/w500${backdrop.filePath}"
                CarouselItem(imageUrl = imageUrl)
            }
            return backdropImages ?: emptyList()
        }
        return emptyList()
    }
}