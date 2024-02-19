package com.example.currency_changer_app.viewmodel

import androidx.lifecycle.ViewModel
import com.example.currency_changer_app.model.Balance
import com.example.currency_changer_app.model.User
import com.example.currency_changer_app.util.ACTIVE_USER
import com.example.currency_changer_app.util.BALANCE_REPOSITORY
import kotlinx.coroutines.runBlocking

class ProfileViewModel: ViewModel() {
    fun getUserBalance(userId: Long) : List<Balance> {
        return runBlocking {
            BALANCE_REPOSITORY.getBalanceByUserId(userId)
        }
    }

    fun logout() {
        val emptyUser = User()
        ACTIVE_USER = emptyUser
    }
}