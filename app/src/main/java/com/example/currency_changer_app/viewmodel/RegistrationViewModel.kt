package com.example.currency_changer_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currency_changer_app.model.User
import com.example.currency_changer_app.util.USER_EMAIL
import com.example.currency_changer_app.util.USER_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel: ViewModel() {
    fun saveUser(user: User) =
        viewModelScope.launch (Dispatchers.IO) {
            USER_REPOSITORY.insert(user)
            USER_EMAIL = user.email
        }
}