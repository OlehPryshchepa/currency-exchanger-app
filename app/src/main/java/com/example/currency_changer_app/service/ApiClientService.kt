package com.example.currency_changer_app.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import com.example.currency_changer_app.dto.CurrencyRates
import com.example.currency_changer_app.util.Constant
import org.json.JSONObject
import java.util.concurrent.TimeUnit


class ApiClientService(private val url: String,private val context: Context) {

    fun fetchDataFromApi(onSuccess: (CurrencyRates) -> Unit, onError: (String) -> Unit) {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                onSuccess(CurrencyRates.fromJson(response))
            },
            { _ ->
                onError(Constant.INTERNET_PERMiSION_ERROR_MESSAGE)
            }
        )
        Volley.newRequestQueue(context).add(jsonObjectRequest)
    }

    //    fun fetchDataFromApi(): CurrencyRates? {
//        val request = RequestFuture.newFuture<JSONObject>()
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.GET,
//            url,
//            null,
//            request,
//            request
//        )
//
//        // Добавляем запрос в очередь запросов Volley
//        Volley.newRequestQueue(context).add(jsonObjectRequest)
//
//        try {
//            val response = request.get(10, TimeUnit.SECONDS)
//            // Обработка успешного ответа от сервера
//            return CurrencyRates.fromJson(response)
//        } catch (e: Exception) {
//            // Обработка ошибки
//            Log.e("ApiClientService", "Error fetching data from API: ${e.message}")
//            return null
//        }
//    }

}
/*
* class ApiClientService() {
*   companion object {
*   private var ApiClientService: ApiClientService ?= null
*   @Synchronized
*   fun getInstance(context: Context, url: String) {
*
* }
* }
* }
*
* */