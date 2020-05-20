package com.davidmendozamartinez.mvp.sample

interface LoginInteractor {
    fun login(email: String, password: String, listener: OnLoginFinishedListener)
}