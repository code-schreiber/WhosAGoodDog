package com.toolslab.gooddog.di

import android.app.Application
import com.toolslab.gooddog.GoodDog
import com.toolslab.gooddog.base_repository.di.RepositoryComponent
import com.toolslab.gooddog.view.login.LoginModule
import com.toolslab.gooddog.view.mydog.MyDogModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ActivitiesBindingModule::class,
            LoginModule::class,
            MyDogModule::class
        ],
        dependencies = [
            RepositoryComponent::class
        ]
)
interface AppComponent : AndroidInjector<GoodDog> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder

        fun repositoryComponent(repositoryComponent: RepositoryComponent): Builder

        fun build(): AppComponent
    }
}
