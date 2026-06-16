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
import com.example.filbert_chrome.databinding.ActivityLogBinding
import com.google.android.material.tabs.TabLayoutMediator

class LogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbarLog)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Setup ViewPager2
        val adapter = LogPagerAdapter(this)
        binding.viewPager.adapter = adapter

        // Hubungkan TabLayout dengan 4 Kategori
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Sengketa"
                1 -> "Persil"
                2 -> "Sistem"
                3 -> "Admin"
                else -> ""
            }
        }.attach()

        // FAB Click Listener to add new log
        binding.fabAddLog.setOnClickListener {
            val addLogFragment = AddLogFragment()
            addLogFragment.show(supportFragmentManager, AddLogFragment.TAG)
        }
    }

    // Function to refresh the current fragment data
    fun refreshCurrentFragment() {
        val fragment = supportFragmentManager.findFragmentByTag("f" + binding.viewPager.currentItem)
        when (fragment) {
            is SengketaFragment -> fragment.onResume()
            is PersilFragment -> fragment.onResume()
            is SistemFragment -> fragment.onResume()
            is AdminFragment -> fragment.onResume()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    class LogPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SengketaFragment()
                1 -> PersilFragment()
                2 -> SistemFragment()
                3 -> AdminFragment()
                else -> throw IllegalStateException("Posisi tidak valid")
            }
        }
    }
}
