package com.wild.mdb.ui.discover

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wild.mdb.MVVMApplication
import com.wild.mdb.data.model.Movie
import com.wild.mdb.databinding.DiscoverActivityLayoutBinding
import com.wild.mdb.di.component.DaggerActivityComponent
import com.wild.mdb.di.module.ActivityModule
import com.wild.mdb.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverActivity: AppCompatActivity() {
    @Inject
    lateinit var discoverViewModel: DiscoverViewModel

    @Inject
    lateinit var adapter: DiscoverAdapter

    private lateinit var binding: DiscoverActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = DiscoverActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
        setupObserver()
    }

    private fun setUI() {
//        val genre = Genre(
//            id = intent.getIntExtra("id",0),
//            name = intent.getStringExtra("name").toString()
//        )

        val rvMovies = binding.rvMovies
        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                discoverViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.rvMovies.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.rvMovies.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@DiscoverActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(movies: List<Movie>) {
        adapter.addMovies(movies)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MVVMApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}