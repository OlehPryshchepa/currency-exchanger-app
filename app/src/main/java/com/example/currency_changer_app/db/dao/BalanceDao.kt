package com.example.currency_changer_app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.currency_changer_app.model.Balance

@Dao
interface BalanceDao {
    @Query("SELECT * FROM balances WHERE userId = :userId")
    suspend fun getBalanceByUserId(userId: Long): List<Balance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(balance: Balance)

    @Update
    suspend fun update(balance: Balance)

    @Delete
    suspend fun delete(balance: Balance)
}