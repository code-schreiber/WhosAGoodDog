package com.toolslab.gooddog

import com.facebook.drawee.backends.pipeline.Fresco
import com.toolslab.gooddog.base_repository.di.DaggerRepositoryComponent
import com.toolslab.gooddog.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

class GoodDog : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val repositoryComponent = DaggerRepositoryComponent.builder().build()
        return DaggerAppComponent
                .builder()
                .create(this)
                .repositoryComponent(repositoryComponent)
                .build()
    }

}
