package com.example.movietmdb

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.movietmdb.placeholder.PlaceholderContent.PlaceholderItem
import com.example.movietmdb.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */

//Interface to render details element option
interface MovieItemListener {

    fun onItemSelected(position: Int)
}

class MyMovieRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,

    //Instantiating interface
    private val listener: MovieItemListener

) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

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
        holder.idView.text = item.id
        holder.contentView.text = item.content

        holder.view.setOnClickListener {
            listener.onItemSelected(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        //Instantiating view to couple with object at click time
        val view: View = binding.root
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}