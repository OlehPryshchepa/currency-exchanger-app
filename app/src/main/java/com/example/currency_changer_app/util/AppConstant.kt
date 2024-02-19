package com.example.currency_changer_app.util

import com.example.currency_changer_app.db.repository.BalanceRepository
import com.example.currency_changer_app.db.repository.UserRepository
import com.example.currency_changer_app.model.User

lateinit var ACTIVE_USER: User
lateinit var USER_REPOSITORY: UserRepository
lateinit var BALANCE_REPOSITORY: BalanceRepository
var DEFAULT_CURRENCY_FOR_NEW_USER = "EUR"
var DEFAULT_CURRENCY_AMOUNT_FOR_NEW_USER = 1000.0
var EMAIL_PATTERN = """^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""".toRegex()
var CURRENCY_API_URL = "https://developers.paysera.com/tasks/api/currency-exchange-rates"
var INTERNET_PERMiSION_ERROR_MESSAGE = "Application has`t internet permission"

