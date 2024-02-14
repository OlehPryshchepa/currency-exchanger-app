package com.example.currency_changer_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currency_changer_app.db.dao.UserDao
import com.example.currency_changer_app.model.Balance
import com.example.currency_changer_app.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        private var database: AppDataBase ?= null

        @Synchronized
        fun getInstance(context: Context): AppDataBase {
          return if (database == null) {
              database = Room.databaseBuilder(context, AppDataBase::class.java, "db").build()
              database as AppDataBase
          } else {
              database as AppDataBase
          }
        }
    }
}