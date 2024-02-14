package com.example.currency_changer_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.currency_changer_app.databinding.ActivityLoginBinding
import com.example.currency_changer_app.util.APP
import com.example.currency_changer_app.viewmodel.StartLoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var startLoginViewModel: StartLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP = this
        init()
        binding.loginButton.setOnClickListener {
            login()
            val intent = Intent(this, MainActivity::class.java)//Intent(this, RegistrationActivity::class.java)
            Toast.makeText(this, "Login button", Toast.LENGTH_SHORT).show()
            startActivity(intent)
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
        startLoginViewModel.getUser(binding.emailInputText.text.toString())
    }
}