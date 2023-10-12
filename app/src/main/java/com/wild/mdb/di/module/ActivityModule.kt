package com.wild.mdb.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.wild.mdb.data.repository.ServerRepository
import com.wild.mdb.di.ActivityContext
import com.wild.mdb.ui.base.ViewModelProviderFactory
import com.wild.mdb.ui.detail.DetailViewModel
import com.wild.mdb.ui.discover.DiscoverAdapter
import com.wild.mdb.ui.discover.DiscoverViewModel
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
    fun provideDiscoverViewModel(serverRepo: ServerRepository): DiscoverViewModel {
        return  ViewModelProvider(activity, ViewModelProviderFactory(DiscoverViewModel::class) {
            DiscoverViewModel(serverRepo)
        })[DiscoverViewModel::class.java]
    }

    @Provides
    fun provideDiscoverAdapter() = DiscoverAdapter(ArrayList())

    @Provides
    fun provideGenreViewModel(serverRepo: ServerRepository): GenreViewModel {
        return  ViewModelProvider(activity, ViewModelProviderFactory(GenreViewModel::class) {
            GenreViewModel(serverRepo)
        })[GenreViewModel::class.java]
    }

    @Provides
    fun provideGenreAdapter() = GenreAdapter(ArrayList())

    @Provides
    fun provideDetailViewModel(serverRepo: ServerRepository): DetailViewModel{
        return ViewModelProvider(activity, ViewModelProviderFactory(DetailViewModel::class){
            DetailViewModel(serverRepo)
        })[DetailViewModel::class.java]
    }
}