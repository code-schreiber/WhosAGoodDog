package com.toolslab.gooddog.view.base

import com.toolslab.gooddog.base_mvp.BaseView
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment(), BaseView {

    override fun showNoConnectionError() {
        (activity as BaseActivity).showNoConnectionError()
    }

    override fun showDefaultError() {
        (activity as BaseActivity).showDefaultError()
    }

    override fun showMessage(message: String) {
        (activity as BaseActivity).showMessage(message)
    }

}

