package com.example.currency_changer_app.db.repository

import androidx.lifecycle.LiveData
import com.example.currency_changer_app.model.User

interface UserRepository {

    fun getByEmail(email: String): LiveData<User>

    suspend fun insert(user: User)

    suspend fun update(user: User)

    suspend fun delete(user: User)
}