package com.wild.mdb.di.component

import com.wild.mdb.di.ActivityScope
import com.wild.mdb.di.module.ActivityModule
import com.wild.mdb.ui.discover.DiscoverActivity
import com.wild.mdb.ui.genre.GenreActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: GenreActivity)
    fun inject(activity: DiscoverActivity)
}