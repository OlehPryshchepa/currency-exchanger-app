package com.example.currency_changer_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.currency_changer_app.databinding.ActivityRegistrationBinding
import com.example.currency_changer_app.model.User
import com.example.currency_changer_app.util.USER_EMAIL
import com.example.currency_changer_app.viewmodel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            init()
            val intent = Intent(this, LoginActivity::class.java)//Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun init() {
        val startLoginViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        val email = binding.emailInputText.text.toString()
        val username = binding.usernameInputText.text.toString()
        val password = binding.passwordInputText.text.toString()
        startLoginViewModel.saveUser(User(email = email, userName = username, password = password))
    }
}