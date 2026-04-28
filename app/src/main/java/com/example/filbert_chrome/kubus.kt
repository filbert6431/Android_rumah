package com.example.filbert_chrome

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.filbert_chrome.databinding.ActivityKubusBinding

class kubus : AppCompatActivity() {
    private lateinit var binding: ActivityKubusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityKubusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputsisi = findViewById<EditText>(R.id.sisi_sisi)
        val btn_luas_permukaan_kubus = findViewById<Button>(R.id.btn_luas_permukaan_Kubus)
        val btn_volume_kubus= findViewById<Button>(R.id.btn_Volume_Kubus)
        val text_hasil_kubus = findViewById<TextView>(R.id.Hasil_Kubus)

        val judul = intent.getStringExtra("judul_halaman")
        val description = intent.getStringExtra("description")

        binding.judulHalaman.text = judul
        binding.description.text = description

        btn_luas_permukaan_kubus.setOnClickListener {

            val sisi = inputsisi.text.toString().toDouble()

            val hasil_kubus = 2 * sisi * sisi

            text_hasil_kubus.visibility = View.VISIBLE
            text_hasil_kubus.text = "Luas Permukaan Kubus adalah : " + hasil_kubus.toString()

        }

        btn_volume_kubus.setOnClickListener {
            val sisi = inputsisi.text.toString().toDouble()

            val hasil_kubus = sisi * sisi * sisi

            text_hasil_kubus.visibility = View.VISIBLE
            text_hasil_kubus.text = "Volume kubus adalah : " + hasil_kubus.toString()

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}