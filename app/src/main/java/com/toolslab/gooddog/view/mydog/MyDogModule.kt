package com.toolslab.gooddog.view.mydog

import dagger.Module
import dagger.Provides

@Module
class MyDogModule {

    @Provides
    internal fun providePresenter(presenter: MyDogPresenter): MyDogContract.Presenter = presenter

}
