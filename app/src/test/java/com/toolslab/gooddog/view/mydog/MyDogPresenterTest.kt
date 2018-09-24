package com.toolslab.gooddog.view.mydog

import com.nhaarman.mockito_kotlin.*
import com.toolslab.gooddog.base_repository.exception.NoConnectionException
import com.toolslab.gooddog.base_repository.exception.UnavailableException
import com.toolslab.gooddog.base_repository.model.DogImage
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class MyDogPresenterTest {

    private val breed = "a breed"
    private val imageUrl = "a imageUrl"
    private val dogImage = DogImage(breed, imageUrl)
    private val exception = Exception()

    private val mockView: MyDogContract.View = mock()

    private val underTest = MyDogPresenter()

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() {
            val immediate = object : Scheduler() {

                // this prevents StackOverflowErrors when scheduling with a delay
                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit) = super.scheduleDirect(run, 0, unit)

                override fun createWorker() = ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }

            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
            RxJavaPlugins.setIoSchedulerHandler { immediate }
        }

    }

    @Before
    fun setUp() {
        underTest.compositeDisposable = mock()
        underTest.dogImageRepository = mock()

        whenever(underTest.dogImageRepository.randomDogImage()).thenReturn(Single.just(dogImage))

        underTest.bind(mockView)
    }

    @Test
    fun onBound() {
        verify(mockView).setBreedText(breed)
        verify(mockView).setImageUrl(imageUrl)
        verify(underTest.compositeDisposable).add(any())
    }

    @Test
    fun onUnbound() {
        underTest.onUnbound(mockView)

        verify(underTest.compositeDisposable).clear()
    }

    @Test
    fun onThatsNotMyDogClicked() {
        underTest.onThatsNotMyDogClicked()

        verify(mockView, times(2)).setBreedText(breed)
        verify(mockView, times(2)).setImageUrl(imageUrl)
        verify(underTest.compositeDisposable, times(2)).add(any())
    }

    @Test
    fun onThatsNotMyDogClickedWithUnavailableError() {
        whenever(underTest.dogImageRepository.randomDogImage()).thenReturn(Single.error(UnavailableException(exception)))

        underTest.onThatsNotMyDogClicked()

        verify(mockView).showUnavailableError()
        verify(underTest.compositeDisposable, times(2)).add(any())
    }

    @Test
    fun onThatsNotMyDogClickedWithNoConnectionError() {
        whenever(underTest.dogImageRepository.randomDogImage()).thenReturn(Single.error(NoConnectionException(exception)))

        underTest.onThatsNotMyDogClicked()

        verify(mockView).showNoConnectionError()
        verify(underTest.compositeDisposable, times(2)).add(any())
    }

    @Test
    fun onThatsNotMyDogClickedWithDefaultError() {
        whenever(underTest.dogImageRepository.randomDogImage()).thenReturn(Single.error(exception))

        underTest.onThatsNotMyDogClicked()

        verify(mockView).showDefaultError()
        verify(underTest.compositeDisposable, times(2)).add(any())
    }

}
