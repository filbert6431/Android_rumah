package com.example.filbert_chrome.Persil

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.filbert_chrome.Data.AppDatabase
import com.example.filbert_chrome.Data.entity.LogEntity
import com.example.filbert_chrome.Data.entity.PersilEntity
import com.example.filbert_chrome.databinding.ActivityFormPersilBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FormPersilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormPersilBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormPersilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { 
            finish()
        }

        binding.btnSaveNote.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val nomorObjek = binding.namaObjek.text.toString()
        val namaPemilik = binding.namaPemilik.text.toString()
        val luasTanah = binding.luasTanah.text.toString()
        val alamat = binding.alamat.text.toString()
        val jenisTanah = binding.JenisTanah.text.toString()

        if (nomorObjek.isEmpty() || namaPemilik.isEmpty()) {
            Toast.makeText(this, "Nomor Objek dan Nama Pemilik wajib diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            // 1. Simpan Data Persil
            val persil = PersilEntity(
                nomor_objek = nomorObjek,
                nama_Pemilik = namaPemilik,
                luas_tanah = luasTanah,
                alamat = alamat,
                jenis_tanah = jenisTanah
            )
            db.PersilDao().insert(persil)

            // 2. CATAT LOG OTOMATIS (Point 5)
            val currentTime = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())
            val logEntry = LogEntity(
                title = "Penambahan Persil Baru",
                description = "Persil NOP $nomorObjek atas nama $namaPemilik berhasil ditambahkan.",
                category = "Persil",
                timestamp = currentTime
            )
            db.LogDao().insertLog(logEntry)

            Toast.makeText(this@FormPersilActivity, "Data Persil Berhasil Disimpan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
