package com.example.bookappreview.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bookappreview.presentation.fragment.AddLivroFragment
import com.example.bookappreview.presentation.fragment.HomeFragment
import com.example.bookappreview.presentation.fragment.screens.ChatFragment
import com.example.bookappreview.presentation.fragment.screens.ProfileFragment

/**
 * Adapter para o Viewpager
 * Ele que gerencia os fragmentos para cada aba
 */
class ViewPagerAdapter(
   fa: FragmentActivity
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ChatFragment()
            1 -> AddLivroFragment()
            2 -> HomeFragment()
            else -> ChatFragment()
        }
    }


}