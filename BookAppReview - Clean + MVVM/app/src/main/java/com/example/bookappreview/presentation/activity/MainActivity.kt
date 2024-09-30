package com.example.bookappreview.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.bookappreview.R
import com.example.bookappreview.presentation.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.my_view_pager)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator (tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "chats"
                1  -> tab.text = "Profile"
                2 -> tab.text = "Home"
            }

        }.attach()
    }

    /**
     * Use navController or the parent default class
     */
//    override fun onSupportNavigateUp(): Boolean {
////        navController = findNavController(R.id.nav_host_fragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}