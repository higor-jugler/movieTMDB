package com.example.movietmdb.movieHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietmdb.MovieViewModel
import com.example.movietmdb.R
import com.example.movietmdb.ViewDataState
import com.example.movietmdb.databinding.FragmentItemListBinding

class MovieFragment : Fragment(), MovieItemListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var movieList: RecyclerView
    private lateinit var errorTextView: TextView

    private lateinit var adapter: MyMovieRecyclerViewAdapter

    //Linking fragment with view model
    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.movie_graph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentItemListBinding.inflate(inflater)
        val view = binding.root

        progressBar = binding.progressBarList
        movieList = binding.list
        errorTextView = binding.errorTextView

        val layoutManager = LinearLayoutManager(context)
        adapter = MyMovieRecyclerViewAdapter(this)

        movieList.apply {
            this.adapter = this@MovieFragment.adapter
            this.layoutManager = layoutManager
        }
        initObservers(progressBar, movieList, errorTextView)
        return view
    }

    private fun initObservers(
        progressBar: ProgressBar,
        movieList: RecyclerView,
        errorTextView: TextView
    ) {
        viewModel.movieListLiveData.observe(viewLifecycleOwner, Observer {

            it?.let {

                adapter.updateDate(it)
            }
        })

        viewModel.viewDataStateLiveData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                ViewDataState.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    movieList.visibility = View.GONE
                    errorTextView.visibility = View.GONE
                }

                ViewDataState.ERROR -> {
                    progressBar.visibility = View.GONE
                    movieList.visibility = View.GONE
                    errorTextView.visibility = View.VISIBLE
                    errorTextView.text = "Failed to load list"
                }

                ViewDataState.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    movieList.visibility = View.VISIBLE
                    errorTextView.visibility = View.GONE
                }

                else -> {
                    // When the value is null
                    progressBar.visibility = View.GONE
                    movieList.visibility = View.GONE
                    errorTextView.visibility = View.VISIBLE
                    errorTextView.text = "Unknown error"
                }
            }
        })

        // Integrate Nav
        viewModel.navigationToDetailLiveData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandle()?.let {
                val action = MovieFragmentDirections.actionMoviesFragmentToMoviesDetailsFragment()
                findNavController().navigate(action)
            }
        })
    }

    // Past the navigation to the position to be rendered
    override fun onItemSelected(position: Int) {
        viewModel.onMovieSelected(position)
    }
}