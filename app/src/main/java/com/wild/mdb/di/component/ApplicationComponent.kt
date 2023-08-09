package com.wild.mdb.di.component

import android.content.Context
import com.wild.mdb.MVVMApplication
import com.wild.mdb.data.api.NetworkService
import com.wild.mdb.data.repository.GenreRepository
import com.wild.mdb.di.ApplicationContext
import com.wild.mdb.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: MVVMApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getGenreRepository(): GenreRepository
}