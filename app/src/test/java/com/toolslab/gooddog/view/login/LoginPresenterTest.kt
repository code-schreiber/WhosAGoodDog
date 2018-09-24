package com.toolslab.gooddog.view.login

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test

class LoginPresenterTest {

    private val username = "username"

    private val mockView: LoginContract.View = mock()

    private val underTest = LoginPresenter()

    @Before
    fun setUp() {
        underTest.dummyLogin = mock()
    }

    @Test
    fun onBound() {
        whenever(underTest.dummyLogin.isLoggedIn()).thenReturn(false)

        underTest.bind(mockView)

        verifyZeroInteractions(mockView)
    }

    @Test
    fun onBoundWhenLoggedIn() {
        whenever(underTest.dummyLogin.isLoggedIn()).thenReturn(true)
        whenever(underTest.dummyLogin.getUsername()).thenReturn(username)

        underTest.bind(mockView)

        verify(mockView).showLoginMessage(username)
        verify(mockView).startMainActivity()
    }

    @Test
    fun onLoginButtonClicked() {
        underTest.bind(mockView)

        underTest.onLoginButtonClicked(username)

        verify(mockView).showProgress(true)
        verify(mockView).resetErrors()
        verify(underTest.dummyLogin).logIn(username)
        verify(mockView).showLoginMessage(username)
        verify(mockView).showProgress(false)
        verify(mockView).startMainActivity()
    }

    @Test
    fun onLoginButtonClickedWithNoUsername() {
        underTest.bind(mockView)

        underTest.onLoginButtonClicked("")

        verify(mockView).showFieldRequiredError()
        verify(mockView).showProgress(false)
    }


}
