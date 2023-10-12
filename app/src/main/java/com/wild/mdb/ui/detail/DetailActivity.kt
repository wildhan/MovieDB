package com.wild.mdb.ui.detail

import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.Currency
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.wild.mdb.MVVMApplication
import com.wild.mdb.R
import com.wild.mdb.databinding.DetailActivityLayoutBinding
import com.wild.mdb.di.component.DaggerActivityComponent
import com.wild.mdb.di.module.ActivityModule
import com.wild.mdb.ui.base.UiState
import com.wild.mdb.utils.AppConstant
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

class DetailActivity: AppCompatActivity() {
    @Inject
    lateinit var detailViewModel: DetailViewModel

    private lateinit var binding: DetailActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = DetailActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObserver()
        detailViewModel.fetchDetailMovie(intent.getIntExtra("id",-1))
    }
    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.uiState.collect{
                    when(it){
                        is UiState.Loading -> {

                        }
                        is UiState.Success -> {
                            val sdfInput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val sdfOutput = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                            val parse = sdfInput.parse(it.data.releaseDate)

                            val formatter = NumberFormat.getCurrencyInstance()
                            formatter.currency = Currency.getInstance("USD")

                            binding.tvOverview.text = it.data.overview
                            binding.tvTitle.text = it.data.title
                            binding.tvTagline.text = it.data.tagline
                            binding.tvReleaseDate.text = getString(R.string.release_date, sdfOutput.format(parse))
                            binding.tvRevenue.text = getString(R.string.revenue, formatter.format(it.data.revenue))
                            binding.tvRuntime.text = getString(R.string.runtime, resources.getQuantityString(R.plurals.runtime,it.data.runtime,it.data.runtime))
                            Glide.with(binding.ivBackdrop.context)
                                .load(AppConstant.IMG_URL + it.data.backdropPath)
                                .into(binding.ivBackdrop)
                        }
                        is UiState.Error -> {

                        }
                    }
                }
            }
        }
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MVVMApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}