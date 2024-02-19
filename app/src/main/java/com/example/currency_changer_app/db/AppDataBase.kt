package com.example.currency_changer_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currency_changer_app.db.dao.BalanceDao
import com.example.currency_changer_app.db.dao.UserDao
import com.example.currency_changer_app.model.Balance
import com.example.currency_changer_app.model.User
import com.example.currency_changer_app.util.DbConstant

@Database(entities = [User::class, Balance::class], version = 5)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getBalanceDao(): BalanceDao

    companion object {
        private var database: AppDataBase ?= null

        @Synchronized
        fun getInstance(context: Context): AppDataBase {
          return if (database == null) {
              database = Room.databaseBuilder(context, AppDataBase::class.java, DbConstant.DB_NAME)
                  .fallbackToDestructiveMigration()
                  .build()
              database as AppDataBase
          } else {
              database as AppDataBase
          }
        }
    }
}