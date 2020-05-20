package com.davidmendozamartinez.mvp.sample

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun setEmailError()
    fun setPasswordError()
    fun showSuccess()
}