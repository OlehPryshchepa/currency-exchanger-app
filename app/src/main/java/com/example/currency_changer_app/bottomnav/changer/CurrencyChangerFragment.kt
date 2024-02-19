package com.example.currency_changer_app.bottomnav.changer

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currency_changer_app.bottomnav.profile.ProfileFragment
import com.example.currency_changer_app.databinding.FragmentCurrencyChangerBinding
import com.example.currency_changer_app.dto.CurrencyRates
import com.example.currency_changer_app.service.ApiClientService
import com.example.currency_changer_app.util.CURRENCY_API_URL
import com.example.currency_changer_app.viewmodel.CurrencyChangerViewModel

class CurrencyChangerFragment: Fragment() {

    private lateinit var binding: FragmentCurrencyChangerBinding
    private lateinit var apiClientService: ApiClientService
    private var currencyRates: CurrencyRates? = null
    private var currenciesMutableList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyChangerBinding.inflate(inflater, container, false)
        apiClientService = ApiClientService(CURRENCY_API_URL, requireContext())
        fetchDataFromApi()
        setUpSpinnerBuyAdapter()
        init()

        return binding.root
    }

    private fun init() {
        val currencyChangerViewModel = ViewModelProvider(this)[CurrencyChangerViewModel::class.java]
        val userBalance = currencyChangerViewModel.getUserBalance()
            .map { balance -> balance.currency }
            .toMutableList()

        val sellAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, userBalance)
        sellAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerToSell.adapter = sellAdapter
        setBuyBtnListener(currencyChangerViewModel)
    }

    private fun fetchDataFromApi() {
        apiClientService.fetchDataFromApi(
            onSuccess = { jsonCurrencyRates ->
                currencyRates = jsonCurrencyRates
                updateSpinnerView(currencyRates!!)
            },
            onError = { error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun updateSpinnerView(currencyRates: CurrencyRates) {
        currenciesMutableList.clear()
        currenciesMutableList.addAll(currencyRates.rates.keys)
        (binding.spinnerToBuy.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }

    private fun setUpSpinnerBuyAdapter() {
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, currenciesMutableList)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerToBuy.adapter = adapter
    }

    private fun setBuyBtnListener(currencyChangerViewModel: CurrencyChangerViewModel) {
        binding.buyButton.setOnClickListener {
            val sellAmountString = binding.currencySellInputText.text.toString()
            if (sellAmountString.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter sell amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val amount = sellAmountString.toDoubleOrNull()
            if (amount == null || amount <= 0) {
                Toast.makeText(requireContext(), "Sell amount must be a valid positive number", Toast.LENGTH_SHORT).show()
            } else {
                val buyRateName = binding.spinnerToBuy.selectedItem.toString()
                val sellRateName = binding.spinnerToSell.selectedItem.toString()

                if (buyRateName == sellRateName) {
                    Toast.makeText(requireContext(), "Buy and sell currencies cannot be the same", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val fragmentManager = requireActivity().supportFragmentManager

                currencyChangerViewModel.handleCurrencyConversion(amount, buyRateName, sellRateName, currencyRates, this)
                fragmentManager.beginTransaction().replace(this.id, ProfileFragment()).commit()
            }
        }
    }
}