package com.example.filbert_chrome.Pertemuan_7

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
import com.example.filbert_chrome.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class Home_fragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    // 1. Deklarasikan adapter di tingkat class agar bisa diakses di semua fungsi
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

        // 2. Inisialisasi RecyclerView segera saat view dibuat agar tidak error
        setupRecyclerView()

        // 3. Panggil API untuk memuat data berita
        loadBerita()
    }

    private fun setupRecyclerView() {
        // Pasang adapter dengan list kosong di awal
        beritaAdapter = BeritaAdapter(mutableListOf())

        // Atur agar susunan kartu berita berjajar ke samping (Horizontal)
        binding.RVBerita.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)

        binding.RVBerita.adapter = beritaAdapter
    }

    private fun loadBerita() {
        // Menjalankan proses asynchronous di dalam Coroutine Lifecycle Scope
        lifecycleScope.launch {
            try {
                // Memanggil endpoint GNews melalui Retrofit Client
                val response = BeritaApiClient.apiService.getBerita(
                    category = "general",
                    language = "id",
                    apiKey = "d440bea70c373255bf4b4a72ef276098"
                )

                // Cek jika daftar artikel tidak kosong, lalu kirim ke adapter
                if (!response.articles.isNullOrEmpty()) {
                    beritaAdapter.updateData(response.articles)
                } else {
                    Toast.makeText(requireContext(), "Berita tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Logcat untuk mempermudah melacak jika terjadi error koneksi/API
                Log.e("DEBUG_BERITA", "Error: ${e.message}")
                Toast.makeText(requireContext(), "Gagal memuat berita", Toast.LENGTH_SHORT).show()
            }
        }
    }
}