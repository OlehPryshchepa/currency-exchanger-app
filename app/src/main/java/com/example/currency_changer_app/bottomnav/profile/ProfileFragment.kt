package com.example.currency_changer_app.bottomnav.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currency_changer_app.LoginActivity
import com.example.currency_changer_app.databinding.FragmentProfileBinding
import com.example.currency_changer_app.recycleview.BalanceItemAdapter
import com.example.currency_changer_app.util.ACTIVE_USER
import com.example.currency_changer_app.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var balanceItemAdapter: BalanceItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        val profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        binding.usernameTextView.text = ACTIVE_USER.userName
        balanceItemAdapter = BalanceItemAdapter(requireContext(),profileViewModel.getUserBalance(
            ACTIVE_USER.id).toMutableList())
        binding.recyclerView.adapter = balanceItemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
        binding.logoutBtn.setOnClickListener {
            profileViewModel.logout()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }
}