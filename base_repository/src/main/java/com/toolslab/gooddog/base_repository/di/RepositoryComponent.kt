package com.toolslab.gooddog.base_repository.di

import com.toolslab.gooddog.base_network.di.NetworkModule
import com.toolslab.gooddog.base_network.service.ApiService
import dagger.Component

@Component(
        modules = [NetworkModule::class]
)
interface RepositoryComponent {

    // Provision method so ApiService gets provided to consumers of this component
    fun provideApiService(): ApiService

    @Component.Builder
    interface Builder {
        fun build(): RepositoryComponent
    }

}
