package com.example.currency_changer_app.db.repository

import androidx.lifecycle.LiveData
import com.example.currency_changer_app.model.User

interface UserRepository {

    suspend fun getByEmail(email: String): User

    suspend fun insert(user: User): Long

    suspend fun update(user: User)

    suspend fun delete(user: User)
}