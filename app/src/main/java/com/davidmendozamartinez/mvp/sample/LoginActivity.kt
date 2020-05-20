package com.davidmendozamartinez.mvp.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.davidmendozamartinez.mvp.sample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var binding: ActivityLoginBinding
    private val presenter = LoginPresenter(this, LoginInteractor(), lifecycleScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            presenter.validateCredentials(
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
    }

    override fun showProgress() {
        binding.progressBarLogin.isVisible = true
    }

    override fun hideProgress() {
        binding.progressBarLogin.isVisible = false
    }

    override fun setEmailError() {
        binding.editTextEmail.error = getString(R.string.error_email)
    }

    override fun setPasswordError() {
        binding.editTextPassword.error = getString(R.string.error_password)
    }

    override fun showSuccess() {
        Toast.makeText(this, R.string.success_login, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}