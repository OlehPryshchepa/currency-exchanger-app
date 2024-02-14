package com.example.currency_changer_app.db.dao

import androidx.room.Dao
import androidx.room.Query

//@Dao
//interface BalanceDao {
//    @Query("SELECT * FROM balances WHERE userId = :userId")
//    fun getBalanceByUserId(userId: Long): LiveData<List<Balance>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(balance: Balance)
//
//    @Update
//    suspend fun update(balance: Balance)
//
//    @Delete
//    suspend fun delete(balance: Balance)
//}