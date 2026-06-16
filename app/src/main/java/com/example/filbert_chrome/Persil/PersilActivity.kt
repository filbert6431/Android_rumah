package com.example.filbert_chrome.Persil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filbert_chrome.Data.AppDatabase
import com.example.filbert_chrome.Data.entity.LogEntity
import com.example.filbert_chrome.Data.entity.PersilEntity
import com.example.filbert_chrome.databinding.ActivityPersilBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PersilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersilBinding
    private lateinit var adapter: PersilAdapter
    private lateinit var db: AppDatabase
    private val persilList = mutableListOf<PersilEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Data Persil Tanah"
        binding.toolbar.setNavigationOnClickListener { finish() }

        db = AppDatabase.getInstance(this)
        
        setupRecyclerView()

        binding.fabAddPersil.setOnClickListener {
            startActivity(Intent(this, FormPersilActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = PersilAdapter(persilList) { item ->
            deletePersil(item)
        }
        binding.rvPersil.layoutManager = LinearLayoutManager(this)
        binding.rvPersil.adapter = adapter
        binding.rvPersil.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun onResume() {
        super.onResume()
        fetchPersil()
    }

    private fun fetchPersil() {
        lifecycleScope.launch {
            val data = db.PersilDao().getAll()
            persilList.clear()
            persilList.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    fun deletePersil(persil: PersilEntity) {
        lifecycleScope.launch {
            // 1. Hapus Data Persil
            db.PersilDao().delete(persil)

            // 2. CATAT LOG OTOMATIS (Penghapusan)
            val currentTime = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())
            val logEntry = LogEntity(
                title = "Penghapusan Data Persil",
                description = "Data Persil NOP ${persil.nomor_objek} milik ${persil.nama_Pemilik} telah dihapus.",
                category = "Persil",
                timestamp = currentTime
            )
            db.LogDao().insertLog(logEntry)

            fetchPersil()
        }
    }
}
