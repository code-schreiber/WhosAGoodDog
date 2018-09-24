package com.toolslab.gooddog.view.mydog

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class MyDogFragmentTest {

    private val underTest = MyDogFragment()

    @Before
    fun setUp() {
        underTest.dogBreed = mock()
        underTest.dogImage = mock()
        underTest.presenter = mock()
    }

    @Test
    fun setBreedText() {
        val breed = "a breed"

        underTest.setBreedText(breed)

        verify(underTest.dogBreed).text = breed
    }

    @Test
    fun setImageUrl() {
        val url = "an url"

        underTest.setImageUrl(url)

        verify(underTest.dogImage).setImageURI(url)
    }

    @Test
    fun onThatsNotMyDogClicked() {
        underTest.onThatsNotMyDogClicked()

        verify(underTest.presenter).onThatsNotMyDogClicked()
    }

}
