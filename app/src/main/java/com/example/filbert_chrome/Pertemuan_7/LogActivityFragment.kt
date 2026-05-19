package com.example.filbert_chrome.Pertemuan_7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import com.example.filbert_chrome.R
import com.example.filbert_chrome.databinding.FragmentLogActivityBinding

class LogActivityFragment : Fragment() {

    private lateinit var binding: FragmentLogActivityBinding

    // Data Master
    private val dataLogAktivitas = listOf(
        mapOf("title" to "Sengketa Baru Terdeteksi", "desc" to "Persil #204 sedang mediasi.", "category" to "Sengketa"),
        mapOf("title" to "Pendaftaran Selesai", "desc" to "Sertifikat Persil #102 terbit.", "category" to "Persil"),
        mapOf("title" to "Update Status", "desc" to "Sengketa #088 kini 'Proses'.", "category" to "Sengketa"),
        mapOf("title" to "Admin Login", "desc" to "Admin Filbert masuk ke sistem.", "category" to "Sistem"),
        mapOf("title" to "Validasi Tanah", "desc" to "Persil #501 telah divalidasi.", "category" to "Persil")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLogActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tampilkan semua data saat pertama kali buka
        tampilkanData(dataLogAktivitas)

        // Logika Klik Chip (Filter)
        binding.chipGroupLog.setOnCheckedStateChangeListener { _, checkedIds ->
            val checkedId = checkedIds.firstOrNull()

            val hasilFilter = when (checkedId) {
                R.id.chipSengketa -> dataLogAktivitas.filter { it["category"] == "Sengketa" }
                R.id.chipPersil -> dataLogAktivitas.filter { it["category"] == "Persil" }
                else -> dataLogAktivitas // Jika klik "Semua" atau tidak ada yang dipilih
            }

            tampilkanData(hasilFilter)
        }
    }

    // Fungsi Simple untuk pasang data ke ListView
    private fun tampilkanData(list: List<Map<String, String>>) {
        val adapter = SimpleAdapter(
            requireContext(),
            list,
            android.R.layout.simple_list_item_2, // Pakai layout bawaan Android (Title & Subtitle)
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listViewItems.adapter = adapter

        // Klik Item
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val item = list[position]
            Toast.makeText(requireContext(), item["title"], Toast.LENGTH_SHORT).show()
        }
    }
}