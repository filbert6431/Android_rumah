package com.example.filbert_chrome.Pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.filbert_chrome.Pertemuan_3.data.Halaman_Welcome
import com.example.filbert_chrome.Pertemuan_3.data.Halaman_login
import com.example.filbert_chrome.R
import com.example.filbert_chrome.databinding.ActivityHalamanUtamaBinding
import com.example.filbert_chrome.databinding.ActivityHalamanWelcomeBinding
import com.example.filbert_chrome.kubus
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class Halaman_utama : AppCompatActivity() {
    private lateinit var binding: ActivityHalamanUtamaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHalamanUtamaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBangunRuang.setOnClickListener {
            val intent  = Intent(this, kubus::class.java)

            intent.putExtra("judul_halaman", "Filbert App made by Filbert")
            intent.putExtra("description", "Aplikasi ini akan selalu bersama anda dimanapun berada")
            startActivity(intent)

        }

        binding.buttonLogin.setOnClickListener {
            val intent  = Intent(this, Halaman_login::class.java)

            intent.putExtra("judul_halaman", "Filbert App made by Filbert")
            intent.putExtra("description", "Aplikasi ini akan selalu bersama anda dimanapun berada")
            startActivity(intent)

        }

        binding.buttonWelcome.setOnClickListener {
            val intent  = Intent(this, Halaman_Welcome::class.java)
            intent.putExtra("judul_halaman", "Filbert App made by Filbert")
            intent.putExtra("description", "Aplikasi ini akan selalu bersama anda dimanapun berada")
            startActivity(intent)
        }

        binding.buttonLogout.setOnClickListener {

                MaterialAlertDialogBuilder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Apakah Anda yakin ingin melanjutkan?")
                    .setPositiveButton("Ya") { dialog, _ ->

                        dialog.dismiss()
                        Log.e("Info Dialog","Anda memilih Login!")

                        val intent = Intent(this, Halaman_login::class.java)
                        startActivity(intent)

                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                        Log.e("Info Dialog","Logout dibatalkan")

                        Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT)

                            .show()
                    }
                    .show()
            }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}