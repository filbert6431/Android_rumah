package com.example.filbert_chrome

import android.R.id.home
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.filbert_chrome.Pertemuan_7.About_fragment
import com.example.filbert_chrome.Pertemuan_7.Home_fragment
import com.example.filbert_chrome.Pertemuan_7.Profil_fragment
import com.example.filbert_chrome.databinding.ActivityBaseBinding

class base_activity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        replaceFragment(Home_fragment())

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId) {
                    R.id.home -> {
                        replaceFragment(Home_fragment())
                        true
                    }

                R.id.about ->  {
                    replaceFragment(About_fragment())
                        true

                }
            R.id.profil ->{
                replaceFragment(Profil_fragment())
                true
            }
                else -> false

        }
        }

    }
private fun replaceFragment(Fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(binding.fragmentContainer.id, Fragment)
        .commit()

}



}