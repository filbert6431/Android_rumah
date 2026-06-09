package com.example.filbert_chrome.Pertemuan_7

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class LogPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Jumlah total tab
    override fun getItemCount(): Int = 3

    // Menentukan Fragment mana yang akan ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SengketaFragment()
            1 -> PersilFragment()
            2 -> SistemFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}