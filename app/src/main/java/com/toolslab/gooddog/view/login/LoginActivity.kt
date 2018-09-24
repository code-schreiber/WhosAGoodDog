package com.toolslab.gooddog.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnEditorAction
import com.toolslab.gooddog.view.base.BaseActivity
import com.toolslab.gooddog.view.nav.MainActivity
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @BindView(R.id.activity_login_progress)
    internal lateinit var progress: ProgressBar

    @BindView(R.id.activity_login_form)
    internal lateinit var form: LinearLayout

    @BindView(R.id.activity_login_username)
    internal lateinit var username: AutoCompleteTextView

    @Inject
    internal lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
        presenter.bind(this)
    }

    override fun showLoginMessage(username: String) {
        showMessage(getString(R.string.logged_is_as, username))
    }

    override fun showProgress(showProgress: Boolean) {
        form.visibility = if (showProgress) View.GONE else View.VISIBLE
        progress.visibility = if (showProgress) View.VISIBLE else View.GONE
    }

    override fun resetErrors() {
        username.error = null
    }

    override fun showFieldRequiredError() {
        username.error = getString(R.string.error_field_required)
        username.requestFocus()
    }

    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    @OnClick(R.id.activity_login_button)
    fun onLoginButtonClicked() {
        presenter.onLoginButtonClicked(username.text.toString())
    }

    @OnEditorAction(R.id.activity_login_username)
    fun onUsernameEditorActionListener(id: Int): Boolean {
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            onLoginButtonClicked()
            return true
        }
        return false
    }

}
