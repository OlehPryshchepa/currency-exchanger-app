package com.example.currency_changer_app.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_changer_app.databinding.BalanceItemLayoutBinding
import com.example.currency_changer_app.model.Balance

class BalanceItemAdapter(private val context: Context, private val balanceList:MutableList<Balance>)
    : RecyclerView.Adapter<BalanceItemAdapter.BalanceItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceItemViewHolder {
        val binding = BalanceItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return BalanceItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BalanceItemViewHolder, position: Int) {
        val balanceItem = balanceList[position]
        holder.bind(balanceItem)
    }

    override fun getItemCount(): Int {
        return balanceList.size
    }


    class BalanceItemViewHolder(balanceItemLayoutBinding: BalanceItemLayoutBinding)
        : RecyclerView.ViewHolder(balanceItemLayoutBinding.root){

        private val binding = balanceItemLayoutBinding

        fun bind(balance: Balance){
            binding.currencyNameTextView.text = balance.currency
            binding.currencyBalanceAmountTextView.text = "${balance.amount}"
        }

    }
}