package com.davidmendozamartinez.mvp.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.davidmendozamartinez.mvp.sample.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            buttonLogin.setOnClickListener {
                lifecycleScope.launch {
                    progressBarLogin.visibility = View.VISIBLE

                    val errorEmail = async(Dispatchers.IO) {
                        Thread.sleep(2000)
                        getEmailError(editTextEmail.text.toString())
                    }

                    val errorPassword = async(Dispatchers.IO) {
                        Thread.sleep(2000)
                        getPasswordError(editTextPassword.text.toString())
                    }

                    if (errorEmail.await() == null && errorPassword.await() == null) {
                        Toast.makeText(
                            this@LoginActivity, R.string.success_login, Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        editTextEmail.error = errorEmail.await()
                        editTextPassword.error = errorPassword.await()
                    }

                    progressBarLogin.visibility = View.GONE
                }
            }
        }
    }

    private fun getEmailError(email: String): String? =
        if (email.isEmpty()) getString(R.string.error_email) else null

    private fun getPasswordError(password: String): String? =
        if (password.isEmpty()) getString(R.string.error_password) else null
}