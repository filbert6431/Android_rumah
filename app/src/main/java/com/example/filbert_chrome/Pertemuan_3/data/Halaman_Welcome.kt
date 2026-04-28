package com.example.filbert_chrome.Pertemuan_3.data

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.filbert_chrome.R
import com.example.filbert_chrome.databinding.ActivityHalamanWelcomeBinding

class Halaman_Welcome : AppCompatActivity() {
    private lateinit var binding: ActivityHalamanWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHalamanWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val judul = intent.getStringExtra("judul_halaman")
        val description = intent.getStringExtra("description")

        binding.judulHalaman.text = judul
        binding.description.text = description

        binding.LetsgetStarted.setOnClickListener {

            val intent = Intent(this, Halaman_login::class.java)
            startActivity(intent)
        }






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}