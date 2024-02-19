package com.example.currency_changer_app.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.currency_changer_app.bottomnav.changer.CurrencyChangerFragment
import com.example.currency_changer_app.dto.CurrencyRates
import com.example.currency_changer_app.model.Balance
import com.example.currency_changer_app.util.ACTIVE_USER
import com.example.currency_changer_app.util.BALANCE_REPOSITORY
import com.example.currency_changer_app.util.DEFAULT_CURRENCY_FOR_NEW_USER
import kotlinx.coroutines.runBlocking

class CurrencyChangerViewModel : ViewModel() {

    fun getUserBalance(): List<Balance> {
        return runBlocking {
            BALANCE_REPOSITORY.getBalanceByUserId(ACTIVE_USER.id)
        }
    }

    fun handleCurrencyConversion(
        amount: Double,
        buyRateName: String,
        sellRateName: String,
        currencyRates: CurrencyRates?,
        currencyChangerFragment: CurrencyChangerFragment
    ) {
        if (currencyRates == null) {
            showToast(currencyChangerFragment.requireContext(), "Failed to fetch currency rates")
            return
        }

        when {
            sellRateName != DEFAULT_CURRENCY_FOR_NEW_USER ->
                performDoubleCurrencyConversion(amount, sellRateName, buyRateName, currencyRates, currencyChangerFragment)
            buyRateName == DEFAULT_CURRENCY_FOR_NEW_USER ->
                performBuyDefaultCurrency(amount, sellRateName, buyRateName, currencyRates, currencyChangerFragment)
            else ->
                performSingleCurrencyConversion(amount, buyRateName, sellRateName, currencyRates, currencyChangerFragment)
        }
    }

    private fun performSingleCurrencyConversion(
        amount: Double,
        buyCurrencyName: String,
        sellCurrencyName: String,
        currencyRates: CurrencyRates,
        currencyChangerFragment: CurrencyChangerFragment
    ) {
        val rate = currencyRates.rates[buyCurrencyName]
        val balanceOfSellCurrency = getUserBalance().find { it.currency == sellCurrencyName }

        if ((rate != null) && (balanceOfSellCurrency != null) && haveEnoughMoney(balanceOfSellCurrency, amount)) {
            val total = amount * rate
            val buyBalance = mapToBalance(total, buyCurrencyName)
            updateBalance(buyBalance, sellCurrencyName, amount, currencyChangerFragment)
        } else {
            showToast(currencyChangerFragment.requireContext(),
                "You can't sell more than ${balanceOfSellCurrency?.amount} ${balanceOfSellCurrency?.currency}")
        }
    }

    private fun performDoubleCurrencyConversion(
        amount: Double,
        sellCurrencyName: String,
        buyCurrencyName: String,
        currencyRates: CurrencyRates,
        currencyChangerFragment: CurrencyChangerFragment
    ) {
        val fromRate = currencyRates.rates[sellCurrencyName]
        val toRate = currencyRates.rates[buyCurrencyName]
        val balanceOfSellCurrency = getUserBalance().find { it.currency == sellCurrencyName }

        if ((fromRate != null) && (toRate != null) && (balanceOfSellCurrency != null) && haveEnoughMoney(balanceOfSellCurrency, amount)) {
            val sellCurToDefaultCur = amount / fromRate
            val fromDefaultCurToBuyCur = sellCurToDefaultCur * toRate

            val buyBalance = mapToBalance(
                fromDefaultCurToBuyCur,
                buyCurrencyName
            )
            updateBalance(buyBalance, sellCurrencyName, amount, currencyChangerFragment)
        } else {
            showToast(currencyChangerFragment.requireContext(),
                "You can't sell more than ${balanceOfSellCurrency?.amount} ${balanceOfSellCurrency?.currency}")
        }
    }

    private fun performBuyDefaultCurrency(
        amount: Double,
        sellCurrencyName: String,
        buyCurrencyName: String,
        currencyRates: CurrencyRates,
        currencyChangerFragment: CurrencyChangerFragment
    ) {
        val fromRate = currencyRates.rates[sellCurrencyName]
        val toRate = currencyRates.rates[buyCurrencyName]

        val balanceOfSellCurrency = getUserBalance().find { it.currency == sellCurrencyName }

        if ((fromRate != null) && (toRate != null) && (balanceOfSellCurrency != null) && haveEnoughMoney(balanceOfSellCurrency, amount)) {
            updateDefaultBalance(amount, fromRate, sellCurrencyName)
        } else {
            showToast(currencyChangerFragment.requireContext(),
                "You can't sell more than ${balanceOfSellCurrency?.amount} ${balanceOfSellCurrency?.currency}")
        }
    }

    private fun updateBalance(buyBalance: Balance, sellCurrencyName: String, amountOfSell: Double, currencyChangerFragment: CurrencyChangerFragment) {
        runBlocking {
            val allBalances = getUserBalance()

            val sellBalance = allBalances.first { it.currency == sellCurrencyName }

            val optionalBuyBalance = allBalances.find { it.currency == buyBalance.currency }

            if (optionalBuyBalance != null) {
                sellBalance.amount = sellBalance.amount.minus(amountOfSell)
                optionalBuyBalance.amount = optionalBuyBalance.amount.plus(buyBalance.amount)

                BALANCE_REPOSITORY.update(sellBalance)
                BALANCE_REPOSITORY.update(optionalBuyBalance)

                showToast(currencyChangerFragment.requireContext(),
                    "Your updated balance ${optionalBuyBalance.amount} ${optionalBuyBalance.currency}")
            } else {
                sellBalance.amount = sellBalance.amount.minus(amountOfSell)

                BALANCE_REPOSITORY.insert(buyBalance)
                BALANCE_REPOSITORY.update(sellBalance)

                showToast(currencyChangerFragment.requireContext(),
                    "You bought ${buyBalance.amount} ${buyBalance.currency}")
            }
        }
    }

    private fun updateDefaultBalance(
        soldAmount: Double,
        fromRate: Double,
        sellCurrencyName: String
    ) {
        runBlocking {
            val allBalances = getUserBalance()

            val sellBalance = allBalances.first { it.currency == sellCurrencyName }

            val buyBalance = allBalances.find { it.currency == DEFAULT_CURRENCY_FOR_NEW_USER }

            sellBalance.amount = sellBalance.amount.minus(soldAmount)
            buyBalance!!.amount = buyBalance.amount.plus(soldAmount * fromRate)

            BALANCE_REPOSITORY.update(sellBalance)
            BALANCE_REPOSITORY.update(buyBalance)
        }
    }

    private fun mapToBalance(
        amount: Double,
        currencyName: String
    ): Balance {
        return Balance(userId = ACTIVE_USER.id, currency = currencyName, amount = amount)
    }

    private fun haveEnoughMoney(balance: Balance, amountOfSell: Double): Boolean {
        return balance.amount >= amountOfSell
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
