package com.example.currency_changer_app.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.currency_changer_app.dto.CurrencyRates
import com.example.currency_changer_app.util.INTERNET_PERMiSION_ERROR_MESSAGE

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
                onError(INTERNET_PERMiSION_ERROR_MESSAGE)
            }
        )
        Volley.newRequestQueue(context).add(jsonObjectRequest)
    }
}
