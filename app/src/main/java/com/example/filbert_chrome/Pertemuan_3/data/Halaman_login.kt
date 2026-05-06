package com.example.filbert_chrome.Pertemuan_3.data

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.filbert_chrome.R
import com.example.filbert_chrome.RegisterActivity
import com.example.filbert_chrome.base_activity
import com.example.filbert_chrome.databinding.ActivityHalamanLoginBinding
import com.google.android.material.snackbar.Snackbar

class Halaman_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityHalamanLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val judul = intent.getStringExtra("judul_halaman")
        val description = intent.getStringExtra("description")

        binding.judulHalaman.text = judul
        binding.description.text = description

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.tombolRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.tombolLogin.setOnClickListener {
            val userStr = binding.Username.text.toString()
            val passStr = binding.Password.text.toString()

            val regUser = sharedPref.getString("registered_username", "")
            val regPass = sharedPref.getString("registered_password", "")

            if (userStr.isEmpty() || passStr.isEmpty()) {
                Snackbar.make(binding.root, "Username dan Password tidak boleh kosong", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Rule 1: username == password
            val isRule1 = (userStr == passStr)
            
            // Rule 2: match with registered data (pastikan regUser tidak kosong agar tidak bypass jika belum registrasi)
            val isRule2 = (regUser != "" && userStr == regUser && passStr == regPass)

            if (isRule1 || isRule2) {
                // Berhasil login
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", userStr)
                editor.apply()

                val intent = Intent(this, base_activity::class.java)
                intent.putExtra("username", userStr)
                startActivity(intent)
                finish()
            } else {
                // Gagal login
                Snackbar.make(binding.root, "Username atau Password salah", Snackbar.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}