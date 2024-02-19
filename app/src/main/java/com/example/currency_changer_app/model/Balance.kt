package com.example.currency_changer_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currency_changer_app.util.DbConstant

@Entity(tableName = DbConstant.BALANCE_TABLE_NAME)
data class Balance(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val userId: Long,
    val currency: String,
    var amount: Double
)