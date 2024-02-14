package com.example.currency_changer_app.bottomnav.changer

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.currency_changer_app.bottomnav.profile.ProfileFragment
import com.example.currency_changer_app.databinding.FragmentCurrencyChangerBinding
import com.example.currency_changer_app.dto.CurrencyRates
import com.example.currency_changer_app.factory.CurrencyChangerViewModelFactory
import com.example.currency_changer_app.model.Balance
import com.example.currency_changer_app.service.ApiClientService
import com.example.currency_changer_app.util.Constant
import com.example.currency_changer_app.util.CurrencyCalculator
import com.example.currency_changer_app.viewmodel.CurrencyChangerViewModel

class CurrencyChangerFragment: Fragment() {

    private lateinit var binding: FragmentCurrencyChangerBinding
    private lateinit var viewModel: CurrencyChangerViewModel
    private lateinit var apiClientService: ApiClientService
    private var currencyRates: CurrencyRates? = null
    private var currenciesMutableList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyChangerBinding.inflate(inflater, container, false)
        apiClientService = ApiClientService(Constant.CURRENCY_API_URL, requireContext())//
        fetchDataFromApi()//
        setUpSpinnerAdapter()//
        setBuyBtnListener()//
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//    }
//
//    private fun init() {
////        val viewModel = ViewModelProvider(this)[CurrencyChangerViewModel(requireContext(), Constant.CURRENCY_API_URL)::class.java]
////        val currencyRates = viewModel.getCurrencyRates()
//        //val factory = CurrencyChangerViewModelFactory(requireContext(), Constant.CURRENCY_API_URL)
//        viewModel = ViewModelProvider(this)[CurrencyChangerViewModel::class.java]
//        val currencyRates = viewModel.getCurrencyRates()
////        setUpSpinnerAdapter()
////        updateSpinnerView(currencyRates)
////        setBuyBtnListener(currencyRates)
//        if (currencyRates != null) {
//            setUpSpinnerAdapter()
//            updateSpinnerView(currencyRates)
//            setBuyBtnListener(currencyRates)
//        } else {
//            // Обработка ситуации, когда данные не удалось получить
//        }
//    }

    private fun fetchDataFromApi() {
        apiClientService.fetchDataFromApi(
            onSuccess = { jsonCurrencyRates ->
                currencyRates = jsonCurrencyRates
                updateSpinnerView(currencyRates!!)
            },
            onError = { error ->
                //Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun updateSpinnerView(currencyRates: CurrencyRates) {
        currenciesMutableList.clear()
        currenciesMutableList.addAll(currencyRates.rates.keys)
        (binding.spinnerToSell.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }

    private fun setUpSpinnerAdapter() {

// Создание адаптера
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, currenciesMutableList)
        //val sellAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listOf("EUR"))
// Устанавливаем стиль выпадающего списка
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        //sellAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
// Установка адаптера для Spinner
        binding.spinnerToSell.adapter = adapter//todo make fetchting data from user balance
        binding.spinnerToBuy.adapter = adapter
    }

    private fun setBuyBtnListener() {
        binding.buyButton.setOnClickListener {
            val amount = binding.currencySellInputText.text.toString().toDouble()
            val rateName = binding.spinnerToBuy.selectedItem.toString()
            val rate = currencyRates!!.rates[rateName]
            val newBalance = CurrencyCalculator.calculate(amount, rate!!, rateName)
            Toast.makeText(requireContext(), "Balance: $newBalance", Toast.LENGTH_LONG).show()
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().replace(this.id,
                ProfileFragment()).commit()
        }
    }
}