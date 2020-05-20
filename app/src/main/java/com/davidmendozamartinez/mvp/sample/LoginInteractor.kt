package com.davidmendozamartinez.mvp.sample

import kotlinx.coroutines.delay

class LoginInteractor {

    interface OnLoginFinishedListener {
        fun onEmailError()
        fun onPasswordError()
        fun onSuccess()
    }

    suspend fun login(email: String, password: String, listener: OnLoginFinishedListener) {
        delay(2000)
        when {
            email.isEmpty() -> listener.onEmailError()
            password.isEmpty() -> listener.onPasswordError()
            else -> listener.onSuccess()
        }
    }
}