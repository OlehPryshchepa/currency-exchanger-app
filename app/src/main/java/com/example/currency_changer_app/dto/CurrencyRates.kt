package com.example.currency_changer_app.dto

import org.json.JSONObject

data class CurrencyRates(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
) {
    companion object {
        fun fromJson(json: JSONObject): CurrencyRates {
            val base = json.getString("base")
            val date = json.getString("date")
            val ratesJson = json.getJSONObject("rates")
            val rates = mutableMapOf<String, Double>()

            val keys = ratesJson.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                val value = ratesJson.getDouble(key)
                rates[key] = value
            }

            return CurrencyRates(base, date, rates)
        }
    }
}
