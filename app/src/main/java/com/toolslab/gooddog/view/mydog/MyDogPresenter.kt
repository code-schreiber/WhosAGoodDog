package com.toolslab.gooddog.view.mydog

import com.toolslab.gooddog.base_mvp.BasePresenter
import com.toolslab.gooddog.base_repository.DogImageRepository
import com.toolslab.gooddog.base_repository.exception.NoConnectionException
import com.toolslab.gooddog.base_repository.exception.UnavailableException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

internal class MyDogPresenter @Inject constructor() :
        BasePresenter<MyDogContract.View>(), MyDogContract.Presenter {

    @Inject
    internal lateinit var compositeDisposable: CompositeDisposable

    @Inject
    internal lateinit var dogImageRepository: DogImageRepository

    override fun onBound(view: MyDogContract.View) {
        fetchRandomDogImage()
    }

    override fun onUnbound(view: MyDogContract.View) {
        compositeDisposable.clear()
    }

    override fun onThatsNotMyDogClicked() {
        fetchRandomDogImage()
    }

    private fun fetchRandomDogImage() {
        compositeDisposable.add(dogImageRepository.randomDogImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.setBreedText(it.breed)
                            view.setImageUrl(it.imageUrl)
                        },
                        {
                            Timber.e(it)
                            when (it) {
                                is NoConnectionException -> view.showNoConnectionError()
                                is UnavailableException -> view.showUnavailableError()
                                else -> view.showDefaultError()
                            }
                        }
                )
        )
    }

}
