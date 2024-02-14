package com.example.currency_changer_app.db.repository

import androidx.lifecycle.LiveData
import com.example.currency_changer_app.model.Balance

interface BalanceRepository {
    fun getBalanceByUserId(userId: Long): LiveData<List<Balance>>

    suspend fun insert(balance: Balance)

    suspend fun update(balance: Balance)

    suspend fun delete(balance: Balance)
}