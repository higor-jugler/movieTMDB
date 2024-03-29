package com.example.movietmdb.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.navGraphViewModels
import com.example.movietmdb.MovieViewModel
import com.example.movietmdb.R
import com.example.movietmdb.databinding.FragmentMovieDetailsBinding

/**
 * This class is responsible for populating the list fragment
 */

class MovieDetailsFragment : Fragment() {
    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.movie_graph) { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Instantiating screen
        val binding: FragmentMovieDetailsBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_movie_details,
                container, false
            )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        // Inflate the layout for this fragment
        return binding.root
    }
}