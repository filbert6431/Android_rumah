package com.example.filbert_chrome

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class hitung : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hitung)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val alas = findViewById<EditText>(R.id.editTextNumberDecimal)
        val tinggi = findViewById<EditText>(R.id.editTextNumberDecimal2)
        val sisi = findViewById<EditText>(R.id.editTextNumberDecimal3)
        val tombol = findViewById<Button>(R.id.button2)
        val hasil = findViewById<TextView>(R.id.textView)

        tombol.setOnClickListener {

            Log.e("HitungActivity", "Tombol Hitung ditekan")

            val a = alas.text.toString().toDoubleOrNull()
            val t = tinggi.text.toString().toDoubleOrNull()
            val s = sisi.text.toString().toDoubleOrNull()

            if (a != null && t != null && s != null) {

                val luasSegitiga = 0.5 * a * t
                val volumeKubus = s * s * s

                hasil.text = """
                    Luas Segitiga = $luasSegitiga
                    Volume Kubus = $volumeKubus
                """.trimIndent()

                Toast.makeText(this, "Perhitungan berhasil", Toast.LENGTH_SHORT).show()

            } else {
                hasil.text = "Input tidak boleh kosong!"
                Toast.makeText(this, "Isi semua input!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}