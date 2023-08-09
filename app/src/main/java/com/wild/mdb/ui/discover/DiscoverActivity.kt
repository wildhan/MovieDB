package com.wild.mdb.ui.discover

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.wild.mdb.data.model.Genre
import com.wild.mdb.databinding.DiscoverActivityLayoutBinding

class DiscoverActivity: AppCompatActivity() {
    private lateinit var binding: DiscoverActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiscoverActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
    }

    private fun setUI() {
        val genre = Genre(
            id = intent.getIntExtra("id",0),
            name = intent.getStringExtra("name").toString()
        )
        val genreName = binding.genreName
        genreName.text = "Hello, Movie ${genre.name}"
    }
}