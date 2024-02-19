package com.example.currency_changer_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.currency_changer_app.databinding.ActivityLoginBinding
import com.example.currency_changer_app.util.ACTIVE_USER
import com.example.currency_changer_app.util.UtilClass
import com.example.currency_changer_app.viewmodel.StartLoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var startLoginViewModel: StartLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        binding.loginButton.setOnClickListener {
            login()
        }

        binding.registerTextView.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }

    private fun init() {
        startLoginViewModel = ViewModelProvider(this)[StartLoginViewModel::class.java]
        startLoginViewModel.initDatabase()

    }

    private fun login() {
        val email = binding.emailInputText.text.toString()
        val password = binding.passwordInputText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_LONG).show()
        } else if (!UtilClass.isValidEmail(email)) {
            Toast.makeText(this, "Email should look like example@gmail.com", Toast.LENGTH_LONG).show()
        } else {
            val user = startLoginViewModel.getUser(email)
            if (user != null && startLoginViewModel.checkPassword(password, user.password, user.salt)) {
                ACTIVE_USER = user
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Wrong password or email", Toast.LENGTH_LONG).show()
            }
        }
    }
}