package com.example.movietmdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.movietmdb.databinding.FragmentItemBinding
import com.example.movietmdb.databinding.FragmentItemListBinding
import com.example.movietmdb.placeholder.PlaceholderContent

class MovieFragment : Fragment(), MovieItemListener {

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
        val view = binding.root as RecyclerView

        adapter = MyMovieRecyclerViewAdapter(this)

        view.apply {
            this.adapter = this@MovieFragment.adapter
            this.layoutManager = LinearLayoutManager(context)
        }
        initObservers()
        return view
    }

    private fun initObservers() {
        viewModel.movieListLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {

                adapter.updateDate(it)
            }
        })

        // Integrate Nav
        viewModel.navigationToDetailLiveData.observe(viewLifecycleOwner, Observer {
            val action = MovieFragmentDirections.actionMoviesFragmentToMoviesDetailsFragment()
            findNavController().navigate(action)
        })
    }

    //Past the navigation to the position to be rendered
    override fun onItemSelected(position: Int) {
        viewModel.onMovieSelected(position)
    }
}