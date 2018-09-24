package com.toolslab.gooddog.base_repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.gooddog.base_network.model.AllBreeds
import com.toolslab.gooddog.base_network.model.ImageResponse
import io.reactivex.Single
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class DogImageRepositoryTest {

    private val breed1 = "breed1"
    private val breed2 = "breed2"
    private val breeds = AllBreeds("", mapOf(Pair(breed1, emptyList()), Pair(breed2, emptyList())))
    private val imageResponse = ImageResponse("", "imageUrl")

    private val underTest = DogImageRepository()

    @Before
    fun setUp() {
        underTest.apiService = mock()
        underTest.errorHandler = mock()
    }

    @Test
    fun listSpaces() {
        whenever(underTest.apiService.listAllBreeds()).thenReturn(Single.just(breeds))
        whenever(underTest.apiService.randomImageOfBreed(breed1)).thenReturn(Single.just(imageResponse))
        whenever(underTest.apiService.randomImageOfBreed(breed2)).thenReturn(Single.just(imageResponse))

        val result = underTest.randomDogImage().blockingGet()

        breeds.message.containsKey(result.breed) shouldEqual true
        result.imageUrl shouldEqual imageResponse.message
    }

    @Test
    fun listSpacesWithError() {
        val exception = Exception("an exception")
        val handledException = Exception("a handled exception")
        whenever(underTest.apiService.listAllBreeds()).thenReturn(Single.error(exception))
        whenever(underTest.errorHandler.handle<AllBreeds>(exception)).thenReturn(Single.error(handledException))

        val testObserver = underTest.randomDogImage().test()
        testObserver.awaitTerminalEvent()

        testObserver.apply {
            valueCount() shouldEqual 0
            errorCount() shouldEqual 1
            errors()[0] shouldEqual handledException
        }
    }

}
