package com.example.currency_changer_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.currency_changer_app.bottomnav.changer.CurrencyChangerFragment
import com.example.currency_changer_app.bottomnav.profile.ProfileFragment
import com.example.currency_changer_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        setDefaultNavItem()
        setNavItemSelectedListener()

    }

    private fun setDefaultNavItem() {
        supportFragmentManager.beginTransaction().replace(mainActivityBinding.fragmentContainer.id,
            ProfileFragment()).commit()
    }

    private fun setNavItemSelectedListener() {
        val fragmentsMap = HashMap<Int, Fragment>()
        fragmentsMap[R.id.profile] = ProfileFragment()
        fragmentsMap[R.id.currency_changer] = CurrencyChangerFragment()

        mainActivityBinding.bottomNav.setOnItemSelectedListener { item ->
            supportFragmentManager.beginTransaction().replace(mainActivityBinding.fragmentContainer.id,
                fragmentsMap[item.itemId]!!
            ).commit()
            return@setOnItemSelectedListener true
        }
    }
}