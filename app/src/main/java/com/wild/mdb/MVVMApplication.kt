package com.wild.mdb

import android.app.Application
import com.wild.mdb.di.component.ApplicationComponent
import com.wild.mdb.di.component.DaggerApplicationComponent
import com.wild.mdb.di.module.ApplicationModule

class MVVMApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}