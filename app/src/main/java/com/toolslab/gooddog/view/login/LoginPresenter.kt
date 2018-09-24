package com.toolslab.gooddog.view.login

import com.toolslab.gooddog.base_mvp.BasePresenter
import javax.inject.Inject

internal class LoginPresenter @Inject constructor() :
        BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    @Inject
    internal lateinit var dummyLogin: DummyLogin

    override fun onBound(view: LoginContract.View) {
        if (dummyLogin.isLoggedIn()) {
            view.showLoginMessage(dummyLogin.getUsername())
            view.startMainActivity()
        }
    }

    override fun onLoginButtonClicked(username: String) {
        view.showProgress(true)

        view.resetErrors()

        if (username.isEmpty()) {
            view.showFieldRequiredError()
            view.showProgress(false)
        } else {
            dummyLogin.logIn(username)
            view.showLoginMessage(username)
            view.showProgress(false)
            view.startMainActivity()
        }
    }

}
