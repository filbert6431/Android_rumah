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
import com.example.filbert_chrome.databinding.FragmentLogSistemBinding

class SistemFragment : Fragment() {
    private var _binding: FragmentLogSistemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLogSistemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Data khusus Sistem
        val dataSistem = listOf(
            LogItem("Admin Login", "Admin Filbert berhasil masuk ke sistem pusat.", "Sistem"),
            LogItem("Backup Otomatis", "Sistem telah mencadangkan database persil desa.", "Sistem"),
            LogItem("Pembaruan Sistem", "Aplikasi telah diperbarui ke versi v1.0.1 Stable.", "Sistem")
        )

        binding.rvSistem.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SistemAdapter(dataSistem)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Adapter khusus Sistem
    class SistemAdapter(private val items: List<LogItem>) : RecyclerView.Adapter<SistemAdapter.ViewHolder>() {
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