package com.example.currency_changer_app.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currency_changer_app.viewmodel.CurrencyChangerViewModel

class CurrencyChangerViewModelFactory(private val context: Context, private val url: String) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(CurrencyChangerViewModel::class.java)) {
//            return CurrencyChangerViewModel(context, url) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
}