package com.example.currency_changer_app.db.repository.impl

import androidx.lifecycle.LiveData
import com.example.currency_changer_app.db.dao.UserDao
import com.example.currency_changer_app.db.repository.UserRepository
import com.example.currency_changer_app.model.User

class UserRepositoryImpl(private val userDao: UserDao): UserRepository{
    override fun getByEmail(email: String): LiveData<User> {
        return userDao.getUser(email)
    }

    override suspend fun insert(user: User) {
        userDao.insert(user)
    }

    override suspend fun update(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(user: User) {
        TODO("Not yet implemented")
    }

}