package com.toolslab.gooddog.base_mvp

interface BaseView : MvpView {
    fun showNoConnectionError()

    fun showDefaultError()

    fun showMessage(message: String)
}
