package com.example.currency_changer_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.currency_changer_app.db.AppDataBase
import com.example.currency_changer_app.db.repository.impl.UserRepositoryImpl
import com.example.currency_changer_app.model.User
import com.example.currency_changer_app.util.USER_EMAIL
import com.example.currency_changer_app.util.USER_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartLoginViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    init {

    }

    fun initDatabase(){
        val userDao = AppDataBase.getInstance(context).getUserDao()
        USER_REPOSITORY = UserRepositoryImpl(userDao)
    }

    fun getUser(email: String): LiveData<User> {
        val userLiveData = USER_REPOSITORY.getByEmail(email)
        USER_EMAIL = userLiveData.value?.email.toString()
        return userLiveData
    }
}