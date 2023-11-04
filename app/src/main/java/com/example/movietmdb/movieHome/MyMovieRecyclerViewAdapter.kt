package com.example.movietmdb.movieHome

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movietmdb.data.Movie

import com.example.movietmdb.databinding.FragmentItemBinding

//Interface to render details element option
interface MovieItemListener {
    fun onItemSelected(position: Int)
}

class MyMovieRecyclerViewAdapter(
    //Instantiating interface
    private val listener: MovieItemListener

) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {
    private val values: MutableList<Movie> = ArrayList()

    fun updateDate(movieList: List<Movie>) {
        values.clear()
        values.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bindItem(item)

        holder.view.setOnClickListener {
            listener.onItemSelected(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val view = binding.root
        fun bindItem(item: Movie) {
            binding.movieItem = item
            binding.executePendingBindings()
        }
    }
}