package com.wild.mdb.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.wild.mdb.data.repository.GenreRepository
import com.wild.mdb.di.ActivityContext
import com.wild.mdb.ui.base.ViewModelProviderFactory
import com.wild.mdb.ui.genre.GenreActivity
import com.wild.mdb.ui.genre.GenreAdapter
import com.wild.mdb.ui.genre.GenreViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @ActivityContext
    @Provides
    fun provideContext(): Context{
        return activity
    }

    @Provides
    fun provideGenreViewModel(genreRepo: GenreRepository): GenreViewModel {
        return  ViewModelProvider(activity, ViewModelProviderFactory(GenreViewModel::class) {
            GenreViewModel(genreRepo)
        })[GenreViewModel::class.java]
    }

    @Provides
    fun provideGenreAdapter() = GenreAdapter(ArrayList())
}