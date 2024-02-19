package com.example.currency_changer_app.db.repository.impl

import com.example.currency_changer_app.db.dao.BalanceDao
import com.example.currency_changer_app.db.repository.BalanceRepository
import com.example.currency_changer_app.model.Balance

class BalanceRepositoryImpl(private val balanceDao: BalanceDao): BalanceRepository {
    override suspend fun getBalanceByUserId(userId: Long): List<Balance> {
        return balanceDao.getBalanceByUserId(userId)
    }

    override suspend fun insert(balance: Balance) {
        balanceDao.insert(balance)
    }

    override suspend fun update(balance: Balance) {
        balanceDao.update(balance)
    }

    override suspend fun delete(balance: Balance) {
        balanceDao.delete(balance)
    }
}