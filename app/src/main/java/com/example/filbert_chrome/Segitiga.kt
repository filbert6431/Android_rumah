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

class Segitiga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_segitiga)



        val inputalas = findViewById<EditText>(R.id.Alas_Segitiga)
        val inputtinggi = findViewById<EditText>(R.id.Tinggi_Segitiga)
        val btnLuas = findViewById<Button>(R.id.btnluas_Segitiga)
        val texthasil = findViewById<TextView>(R.id.Hasil_Segitiga)

        btnLuas.setOnClickListener {
            val alas = inputalas.text.toString().toDouble()
            val tinggi = inputtinggi.text.toString().toDouble()

            val hasil = alas * tinggi

            texthasil.visibility = View.VISIBLE
            texthasil.text = "Luas segitiga adalah : " + hasil.toString()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}