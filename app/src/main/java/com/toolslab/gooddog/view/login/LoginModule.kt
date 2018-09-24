package com.toolslab.gooddog.view.login

import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    internal fun providePresenter(presenter: LoginPresenter): LoginContract.Presenter = presenter

}
