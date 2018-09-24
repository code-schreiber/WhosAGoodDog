package com.toolslab.gooddog.view.login

import com.toolslab.gooddog.base_mvp.BaseView
import com.toolslab.gooddog.base_mvp.MvpPresenter

internal interface LoginContract {

    interface Presenter : MvpPresenter<View> {
        fun onLoginButtonClicked(username: String)
    }

    interface View : BaseView {
        fun showLoginMessage(username: String)

        fun showProgress(showProgress: Boolean)

        fun resetErrors()

        fun showFieldRequiredError()

        fun startMainActivity()
    }

}
