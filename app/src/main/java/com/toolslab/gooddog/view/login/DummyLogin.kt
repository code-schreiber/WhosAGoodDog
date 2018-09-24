package com.toolslab.gooddog.view.login

import android.content.SharedPreferences
import javax.inject.Inject

class DummyLogin @Inject constructor() {

    @Inject
    internal lateinit var sharedPreferences: SharedPreferences

    private val usernameKey = "usernameKey"

    internal fun isLoggedIn() = sharedPreferences.contains(usernameKey)

    internal fun getUsername() = sharedPreferences.getString(usernameKey, "")!!

    internal fun logIn(username: String) {
        sharedPreferences.edit().apply {
            putString(usernameKey, username)
            apply()
        }
    }

}
