package com.wild.mdb.ui.genre

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import com.wild.mdb.MVVMApplication
import com.wild.mdb.data.model.Genre
import com.wild.mdb.databinding.GenreActivityLayoutBinding
import com.wild.mdb.di.component.DaggerActivityComponent
import com.wild.mdb.di.module.ActivityModule
import com.wild.mdb.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenreActivity: AppCompatActivity()  {
    @Inject
    lateinit var genreViewModel: GenreViewModel

    @Inject
    lateinit var adapter: GenreAdapter

    private lateinit var binding: GenreActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = GenreActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setupObserver()
    }

    private fun setUpUI() {
        val rvGenres = binding.rvGenres
        rvGenres.layoutManager = LinearLayoutManager(this)
        rvGenres.addItemDecoration(
            DividerItemDecoration(
                rvGenres.context,
                (rvGenres.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvGenres.adapter = adapter
        rvGenres
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                genreViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.rvGenres.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.rvGenres.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@GenreActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(genres: List<Genre>) {
        adapter.addGenre(genres)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MVVMApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }


}