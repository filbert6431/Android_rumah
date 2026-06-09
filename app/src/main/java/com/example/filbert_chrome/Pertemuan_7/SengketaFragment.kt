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
import com.example.filbert_chrome.databinding.FragmentLogSengketaBinding

class SengketaFragment : Fragment() {
    private var _binding: FragmentLogSengketaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLogSengketaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Data khusus Sengketa
        val dataSengketa = listOf(
            LogItem("Sengketa Batas Tanah", "Persil #204: Mediasi antara Pak Budi dan Pak Andi.", "Sengketa"),
            LogItem("Klaim Ganda", "Sengketa #088: Peninjauan ulang dokumen di pengadilan.", "Sengketa"),
            LogItem("Laporan Tumpang Tindih", "Persil #303: Ditemukan overlap pada peta digital.", "Sengketa")
        )

        binding.rvSengketa.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SengketaAdapter(dataSengketa)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Adapter khusus Sengketa
    class SengketaAdapter(private val items: List<LogItem>) : RecyclerView.Adapter<SengketaAdapter.ViewHolder>() {
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