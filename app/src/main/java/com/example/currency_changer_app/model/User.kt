package com.example.currency_changer_app.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.currency_changer_app.util.Constant

@Entity(tableName = Constant.USER_TABLE_NAME,
    indices = [Index(value = ["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    var email: String,
    var userName:String,
    var password: String,
)