package com.davidmendozamartinez.mvp.sample

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPresenter(
    private var loginView: LoginView?,
    private val loginInteractor: LoginInteractor,
    private val scope: CoroutineScope
) :
    LoginInteractor.OnLoginFinishedListener {

    fun validateCredentials(email: String, password: String) {
        scope.launch {
            loginView?.showProgress()

            withContext(Dispatchers.IO) {
                loginInteractor.login(
                    email,
                    password,
                    this@LoginPresenter
                )
            }
        }
    }

    override fun onEmailError() {
        scope.launch {
            loginView?.apply {
                setEmailError()
                hideProgress()
            }
        }
    }

    override fun onPasswordError() {
        scope.launch {
            loginView?.apply {
                setPasswordError()
                hideProgress()
            }
        }
    }

    override fun onSuccess() {
        scope.launch {
            loginView?.apply {
                showSuccess()
                hideProgress()
            }
        }
    }

    fun onDestroy() {
        loginView = null
    }
}