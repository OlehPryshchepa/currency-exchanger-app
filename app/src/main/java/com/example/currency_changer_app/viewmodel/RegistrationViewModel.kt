package com.example.currency_changer_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currency_changer_app.model.Balance
import com.example.currency_changer_app.model.User
import com.example.currency_changer_app.util.ACTIVE_USER
import com.example.currency_changer_app.util.BALANCE_REPOSITORY
import com.example.currency_changer_app.util.DEFAULT_CURRENCY_AMOUNT_FOR_NEW_USER
import com.example.currency_changer_app.util.DEFAULT_CURRENCY_FOR_NEW_USER
import com.example.currency_changer_app.util.USER_REPOSITORY
import com.example.currency_changer_app.util.UtilClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel: ViewModel() {
    private val _registrationStatus = MutableLiveData<RegistrationStatus>()
    val registrationStatus: LiveData<RegistrationStatus>
        get() = _registrationStatus

    fun saveUser(user: User) =
        viewModelScope.launch(Dispatchers.IO) {
            val existingUser = USER_REPOSITORY.getByEmail(user.email)
            if (existingUser == null) {
                user.salt = UtilClass.generateSalt()
                user.password = UtilClass.hashPassword(user.password, user.salt)
                val userId = USER_REPOSITORY.insert(user)
                ACTIVE_USER = user.copy(id = userId)
                BALANCE_REPOSITORY.insert(Balance(
                    userId = userId,
                    currency = DEFAULT_CURRENCY_FOR_NEW_USER,
                    amount = DEFAULT_CURRENCY_AMOUNT_FOR_NEW_USER))
                _registrationStatus.postValue(RegistrationStatus.Success)
            } else {
                _registrationStatus.postValue(RegistrationStatus.UserExists)
            }
        }
}

enum class RegistrationStatus {
    Success,
    UserExists
}