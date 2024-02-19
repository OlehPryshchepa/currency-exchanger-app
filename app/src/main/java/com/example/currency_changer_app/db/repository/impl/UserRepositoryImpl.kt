package com.example.currency_changer_app.db.repository.impl

import com.example.currency_changer_app.db.dao.UserDao
import com.example.currency_changer_app.db.repository.UserRepository
import com.example.currency_changer_app.model.User

class UserRepositoryImpl(private val userDao: UserDao): UserRepository{
    override suspend fun getByEmail(email: String): User {
        return userDao.getUser(email)
    }

    override suspend fun insert(user: User): Long {
        return userDao.insert(user)
    }

    override suspend fun update(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(user: User) {
        TODO("Not yet implemented")
    }

}