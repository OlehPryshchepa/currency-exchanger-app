package com.example.currency_changer_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.currency_changer_app.databinding.ActivityRegistrationBinding
import com.example.currency_changer_app.model.User
import com.example.currency_changer_app.util.UtilClass
import com.example.currency_changer_app.viewmodel.RegistrationStatus
import com.example.currency_changer_app.viewmodel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            init()
        }
        binding.loginTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        val registrationViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]

        val email = binding.emailInputText.text.toString()
        val username = binding.usernameInputText.text.toString()
        val password = binding.passwordInputText.text.toString()

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email, username, and password cannot be empty", Toast.LENGTH_LONG).show()
        } else if (!UtilClass.isValidEmail(email)) {
            Toast.makeText(this, "Email should look like example@gmail.com", Toast.LENGTH_LONG).show()
        } else {
            registrationViewModel.saveUser(User(email = email, userName = username, password = password, salt = ""))
            registrationViewModel.registrationStatus.observe(this, Observer { status ->
                when (status) {
                    RegistrationStatus.Success -> {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    RegistrationStatus.UserExists -> {
                        Toast.makeText(this, "User with email already exists", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}