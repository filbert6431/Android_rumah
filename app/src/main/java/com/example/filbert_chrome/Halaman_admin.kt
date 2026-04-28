package com.example.filbert_chrome

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.filbert_chrome.databinding.ActivityHalamanAdminBinding

class Halaman_admin : AppCompatActivity() {
    private lateinit var binding: ActivityHalamanAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHalamanAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Halaman Admin"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }


        binding.halamanAdmin.webViewClient = WebViewClient()
        binding.halamanAdmin.settings.javaScriptEnabled = true
        binding.halamanAdmin.loadUrl("https://filbert-project.alwaysdata.net")

        // membuat bisa di zoom
        binding.halamanAdmin.settings.setSupportZoom(true)
        binding.halamanAdmin.settings.builtInZoomControls = true

        // (opsional biar tombol zoom tidak muncul / tapi kalau
        // mau munculkan boleh juga, jadi hapus kode dibawah / beri comment)
        binding.halamanAdmin.settings.displayZoomControls = false

        onBackPressedDispatcher.addCallback(this) {
            if (binding.halamanAdmin.canGoBack()) {
                binding.halamanAdmin.goBack()
            } else {
                finish()
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }



        }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}