package com.example.filbert_chrome.Pertemuan_7

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filbert_chrome.Berita.BeritaAdapter
import com.example.filbert_chrome.Data.API.BeritaApiClient
import com.example.filbert_chrome.Halaman_admin
import com.example.filbert_chrome.Persil.PersilActivity
import com.example.filbert_chrome.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class Home_fragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var beritaAdapter: BeritaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadBerita()

        // 1. Klik ke Halaman Admin (WebView)
        binding.btnwebAdmin.setOnClickListener {
            val intent = Intent(requireContext(), Halaman_admin::class.java)
            startActivity(intent)
        }

        // 2. Klik ke Log Aktivitas Admin (LogActivity)
        binding.btnNotifikasi.setOnClickListener {
            val intent = Intent(requireContext(), LogActivity::class.java)
            startActivity(intent)
        }

        // 3. Klik ke Halaman Data Persil (Tanah)
        binding.btnPersil.setOnClickListener {
            val intent = Intent(requireContext(), PersilActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        beritaAdapter = BeritaAdapter(mutableListOf())
        binding.RVBerita.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)
        binding.RVBerita.adapter = beritaAdapter
    }

    private fun loadBerita() {
        lifecycleScope.launch {
            try {
                val response = BeritaApiClient.apiService.getBerita(
                    category = "general",
                    language = "id",
                    apiKey = "d440bea70c373255bf4b4a72ef276098"
                )

                if (!response.articles.isNullOrEmpty()) {
                    beritaAdapter.updateData(response.articles)
                } else {
                    Toast.makeText(requireContext(), "Berita tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("DEBUG_BERITA", "Error: ${e.message}")
                Toast.makeText(requireContext(), "Gagal memuat berita", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
