package com.example.currency_changer_app.bottomnav.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currency_changer_app.databinding.FragmentProfileBinding
import com.example.currency_changer_app.dto.CurrencyRates
import com.example.currency_changer_app.model.Balance
import com.example.currency_changer_app.recycleview.BalanceItemAdapter
import com.example.currency_changer_app.service.ApiClientService
import com.example.currency_changer_app.util.Constant
import com.example.currency_changer_app.util.USER_EMAIL

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var balanceItemAdapter: BalanceItemAdapter
    private lateinit var apiClientService: ApiClientService
    private var balanceList: MutableList<Balance> = mutableListOf(Balance("EUR", 1000.0))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        apiClientService = ApiClientService(Constant.CURRENCY_API_URL, requireContext())
        setUpAdapter()
        //fetchDataFromApi()
        //initBalance()
        return binding.root
    }

//    private fun fetchDataFromApi() {
//        apiClientService.fetchDataFromApi(
//            onSuccess = { currencyRates ->
//                updateRecyclerView(CurrencyRates.fromJson(currencyRates))
//            },
//            onError = { error ->
//                // Обработка ошибки при получении данных
//            }
//        )
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.usernameTextView.text = USER_EMAIL
    }

    private fun updateRecyclerView(currencyRates: CurrencyRates) {
        for ((currency, amount) in currencyRates.rates) {
            val balance = Balance(currency = currency, amount = amount)
            balanceList.add(balance)
        }
        balanceItemAdapter.notifyDataSetChanged()
    }

    private fun initBalance() {
        for (i in 1..20){
            val name = "Balance Item $i"
            val price = (100 * i).toDouble()

            val balance = Balance(currency = name, amount = price)

            balanceList.add(balance)
        }
    }

    private fun setUpAdapter() {
        balanceItemAdapter = BalanceItemAdapter(requireContext(),balanceList)
        binding.recyclerView.adapter = balanceItemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
    }
}