package com.toolslab.gooddog.view.login

import android.text.Editable
import android.view.inputmethod.EditorInfo
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test

class LoginActivityTest {

    private val username = "username"

    private val mockEditable: Editable = mock()

    private val underTest = LoginActivity()

    @Before
    fun setUp() {
        underTest.username = mock()
        underTest.presenter = mock()

        whenever(underTest.username.text).thenReturn(mockEditable)
        whenever(mockEditable.toString()).thenReturn(username)
    }

    @Test
    fun resetErrors() {
        underTest.resetErrors()

        verify(underTest.username).error = null
    }

    @Test
    fun onLoginButtonClicked() {
        underTest.onLoginButtonClicked()

        verify(underTest.presenter).onLoginButtonClicked(username)
    }

    @Test
    fun onUsernameEditorActionListener() {
        underTest.onUsernameEditorActionListener(EditorInfo.IME_ACTION_DONE)

        verify(underTest.presenter).onLoginButtonClicked(username)
    }

}
