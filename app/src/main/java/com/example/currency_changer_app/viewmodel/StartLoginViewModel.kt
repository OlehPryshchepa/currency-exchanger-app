package com.example.currency_changer_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.currency_changer_app.db.AppDataBase
import com.example.currency_changer_app.db.repository.impl.BalanceRepositoryImpl
import com.example.currency_changer_app.db.repository.impl.UserRepositoryImpl
import com.example.currency_changer_app.model.User
import com.example.currency_changer_app.util.BALANCE_REPOSITORY
import com.example.currency_changer_app.util.USER_REPOSITORY
import com.example.currency_changer_app.util.UtilClass
import kotlinx.coroutines.runBlocking

class StartLoginViewModel(application: Application): AndroidViewModel(application) {

    val context = application

    fun initDatabase(){
        val userDao = AppDataBase.getInstance(context).getUserDao()
        val balanceDao = AppDataBase.getInstance(context).getBalanceDao()
        USER_REPOSITORY = UserRepositoryImpl(userDao)
        BALANCE_REPOSITORY = BalanceRepositoryImpl(balanceDao)
    }

    fun getUser(email: String): User {
        return runBlocking {
            USER_REPOSITORY.getByEmail(email)
        }
    }

    fun checkPassword(password: String, hashedPassword: String, salt: String): Boolean {
        val hashedInput = UtilClass.hashPassword(password, salt)
        return hashedInput == hashedPassword
    }
}
