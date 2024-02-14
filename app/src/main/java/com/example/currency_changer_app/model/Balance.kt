package com.example.currency_changer_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Balance(
    val currency: String,
    val amount: Double
)

//@Entity(tableName = "balances")
//data class Balance(
//    @PrimaryKey(autoGenerate = true)
//    val id: Long = 0L,
//    val userId: Long,
//    val currency: String,
//    val amount: Double
//)