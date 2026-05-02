package com.example.filbert_chrome.Pertemuan_3.data

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.filbert_chrome.R
import com.example.filbert_chrome.base_activity
import com.example.filbert_chrome.databinding.ActivityHalamanLoginBinding

class Halaman_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var binding = ActivityHalamanLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputusername = binding.Username.text.toString()

        val judul = intent.getStringExtra("judul_halaman")
        val description = intent.getStringExtra("description")

        binding.judulHalaman.text = judul
        binding.description.text = description

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        binding.tombolLogin.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putBoolean("isLogin", true)

            // untuk mengirimkan data ke activity ke fragment home
            editor.putString("username",inputusername)
            editor.apply()

        //    intent.putExtra("inputusername", inputusername)


            val intent = Intent(this, base_activity::class.java)



            //
            startActivity(intent)

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}