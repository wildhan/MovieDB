package com.wild.mdb.ui.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wild.mdb.data.model.Movie
import com.wild.mdb.databinding.DiscoverItemLayoutBinding
import com.wild.mdb.utils.AppConstant.IMG_URL

class DiscoverAdapter(private val movies: ArrayList<Movie>) : RecyclerView.Adapter<DiscoverAdapter.DiscoverVH>() {
    class DiscoverVH(private val binding: DiscoverItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie){
            binding.textTitle.text = movie.title

            Glide.with(binding.ivPoster.context)
                .load(IMG_URL + movie.posterPath)
                .into(binding.ivPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DiscoverVH(
            DiscoverItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DiscoverVH, position: Int) = holder.bind(movies[position])

    fun addMovies(newMovies : List<Movie>) {
        movies.addAll(newMovies)
    }

}