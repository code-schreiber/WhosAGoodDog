package com.toolslab.gooddog.view.mydog

import com.toolslab.gooddog.base_mvp.BaseView
import com.toolslab.gooddog.base_mvp.MvpPresenter

internal interface MyDogContract {

    interface Presenter : MvpPresenter<View> {
        fun onThatsNotMyDogClicked()
    }

    interface View : BaseView {
        fun setBreedText(breed: String)

        fun setImageUrl(imageUrl: String)

        fun showUnavailableError()
    }

}
