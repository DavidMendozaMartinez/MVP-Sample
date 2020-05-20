package com.davidmendozamartinez.mvp.sample

interface OnLoginFinishedListener {
    fun onEmailError()
    fun onPasswordError()
    fun onSuccess()
}