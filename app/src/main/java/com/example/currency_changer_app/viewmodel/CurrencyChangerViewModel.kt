package com.example.currency_changer_app.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import com.example.currency_changer_app.dto.CurrencyRates
import com.example.currency_changer_app.service.ApiClientService
import com.example.currency_changer_app.util.Constant
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class CurrencyChangerViewModel(application: Application): AndroidViewModel(application) {
    private lateinit var apiClientService: ApiClientService
    private val context = application
    fun getCurrencyRates(): CurrencyRates? {
        apiClientService = ApiClientService(Constant.CURRENCY_API_URL, context)
        val request = RequestFuture.newFuture<JSONObject>()
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            Constant.CURRENCY_API_URL,
            null,
            request,
            request
        )

        // Добавляем запрос в очередь запросов Volley
        Volley.newRequestQueue(context).add(jsonObjectRequest)

        try {
            val response = request.get(10, TimeUnit.SECONDS)
            // Обработка успешного ответа от сервера
            return CurrencyRates.fromJson(response)
        } catch (e: Exception) {
            // Обработка ошибки
            return null
        }
    }
}