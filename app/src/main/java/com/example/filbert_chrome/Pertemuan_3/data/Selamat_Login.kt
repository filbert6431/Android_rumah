package com.example.filbert_chrome.Pertemuan_3.data

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.filbert_chrome.Halaman_admin
import com.example.filbert_chrome.R
import com.example.filbert_chrome.databinding.ActivitySelamatLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlin.jvm.java

class Selamat_Login : AppCompatActivity() {
    private lateinit var binding: ActivitySelamatLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivitySelamatLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // enggak dipakai lagi karena sudah ada fragment



        // menerima data yang dikirim melewati variabel intent ( i kecil ) dari activity sebelumnya
//        val nama = intent.getStringExtra("inputusername")
//        binding.textView2.text= "Selamat Datang $nama"
//
//        binding.btnwebAdmin.setOnClickListener {
//            val intent = Intent(this, Halaman_admin::class.java)
//            startActivity(intent)
//        }
//
//        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
//        binding.btnLogout.setOnClickListener {
//
//            MaterialAlertDialogBuilder (this)
//                .setTitle("Konfirmasi")
//                .setMessage("Apakah Anda yakin ingin melanjutkan?")
//                .setPositiveButton("Ya") { dialog, _ ->
//                   dialog.dismiss()
//                    val editor = sharedPref.edit()
//                   editor.putBoolean("isLogin", false)
//                   editor.apply()
//
//                    val intent = Intent (this, Halaman_login::class.java)
//                    startActivity(intent)
//            }
//                .setNegativeButton("tidak" ) { dialog, _ ->
//                    dialog.dismiss()
//
//                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT)
//                        .show()
//                }
//                .show()
//
//        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}