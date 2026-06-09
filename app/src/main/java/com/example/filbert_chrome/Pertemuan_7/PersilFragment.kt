package com.example.filbert_chrome.Pertemuan_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filbert_chrome.R
import com.example.filbert_chrome.databinding.FragmentLogPersilBinding

class PersilFragment : Fragment() {
    private var _binding: FragmentLogPersilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLogPersilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Data khusus Persil
        val dataPersil = listOf(
            LogItem("Pendaftaran Selesai", "Persil #102: Sertifikat telah berhasil diterbitkan oleh BPN.", "Persil"),
            LogItem("Validasi Lapangan", "Persil #501: Petugas telah memverifikasi batas koordinat.", "Persil"),
            LogItem("Pengukuran Ulang", "Persil #112: Jadwal pengukuran ulang pada hari Senin depan.", "Persil")
        )

        binding.rvPersil.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PersilAdapter(dataPersil)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class PersilAdapter(private val items: List<LogItem>) : RecyclerView.Adapter<PersilAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvCategory: TextView = view.findViewById(R.id.tvCategory)
            val tvTitle: TextView = view.findViewById(R.id.tvTitle)
            val tvDesc: TextView = view.findViewById(R.id.tvDescription)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_log, parent, false)
        )
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.tvTitle.text = item.title
            holder.tvDesc.text = item.desc
            holder.tvCategory.text = item.category
        }
        override fun getItemCount() = items.size
    }
}