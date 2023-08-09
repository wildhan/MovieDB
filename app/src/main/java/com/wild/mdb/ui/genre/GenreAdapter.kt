package com.wild.mdb.ui.genre

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wild.mdb.data.model.Genre
import com.wild.mdb.databinding.GenreItemLayoutBinding
import com.wild.mdb.ui.discover.DiscoverActivity

class GenreAdapter(private val genres: ArrayList<Genre>) : RecyclerView.Adapter<GenreAdapter.GenreVH>()  {

    class GenreVH(private val binding : GenreItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre : Genre){
            binding.textNameGenre.text = genre.name
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DiscoverActivity::class.java)
                intent.putExtra("id",genre.id)
                intent.putExtra("name",genre.name)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GenreVH(
            GenreItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: GenreVH, position: Int) =
        holder.bind(genres[position])

    fun addGenre(newGenre: List<Genre>) {
        genres.addAll(newGenre)
    }
}