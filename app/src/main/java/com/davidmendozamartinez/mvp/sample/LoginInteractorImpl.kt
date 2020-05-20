package com.davidmendozamartinez.mvp.sample

class LoginInteractorImpl : LoginInteractor {
    override fun login(email: String, password: String, listener: OnLoginFinishedListener) {
        when {
            email.isEmpty() -> listener.onEmailError()
            password.isEmpty() -> listener.onPasswordError()
            else -> listener.onSuccess()
        }
    }
}