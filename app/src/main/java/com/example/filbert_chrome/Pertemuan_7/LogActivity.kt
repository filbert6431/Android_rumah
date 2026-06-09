package com.example.filbert_chrome.Pertemuan_7

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filbert_chrome.R
import com.example.filbert_chrome.databinding.ActivityLogBinding
import com.google.android.material.tabs.TabLayoutMediator

class LogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // POTENSI ERROR: Jika inflate gagal, biasanya karena file activity_log.xml bermasalah atau ID @+id/main tidak ada.
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup Toolbar
        // POTENSI ERROR: Pastikan ID toolbarLog di XML sudah benar, jika tidak aplikasi akan crash (NullPointerException).
        setSupportActionBar(binding.toolbarLog)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Setup ViewPager2 dengan Adapter
        val adapter = LogPagerAdapter(this)
        binding.viewPager.adapter = adapter

        // Hubungkan TabLayout dan ViewPager2
        // POTENSI ERROR: Jika jumlah tab di Mediator berbeda dengan getItemCount di adapter, teks tab akan kosong atau tidak muncul.
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Sengketa"
                1 -> "Persil"
                2 -> "Sistem"
                3 -> "Admin"
                else -> ""
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Mengatur aksi tombol back di Toolbar
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Adapter Internal untuk manajemen Fragment
    class LogPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        
        // POTENSI ERROR: Jika nilai ini diubah tanpa menyesuaikan createFragment, aplikasi akan memicu IllegalStateException.
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            // POTENSI ERROR: Unresolved Reference jika Fragment belum dibuat atau belum di-import.
            return when (position) {
                0 -> SengketaFragment()
                1 -> PersilFragment()
                2 -> SistemFragment()
                // POTENSI ERROR: Muncul jika position tidak ter-handle (misal getItemCount > 3).
                else -> throw IllegalStateException("Posisi tidak valid")
            }
        }
    }
}