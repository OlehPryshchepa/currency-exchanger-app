package com.example.currency_changer_app.util

import com.example.currency_changer_app.model.Balance

class CurrencyCalculator {
    companion object {
        fun calculate(currencyAmount : Double, currencyRate: Double, currencyName: String): Balance {
            val total = currencyAmount * currencyRate;
            return Balance(currencyName, total)
        }
    }
}