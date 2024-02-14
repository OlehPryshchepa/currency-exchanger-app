package com.example.currency_changer_app.util

import com.example.currency_changer_app.LoginActivity
import com.example.currency_changer_app.MainActivity
import com.example.currency_changer_app.db.repository.BalanceRepository
import com.example.currency_changer_app.db.repository.UserRepository
import kotlin.properties.Delegates

lateinit var APP: LoginActivity
lateinit var API_URL: String
lateinit var USER_EMAIL: String
lateinit var USER_REPOSITORY: UserRepository
lateinit var BALANCE_REPOSITORY: BalanceRepository