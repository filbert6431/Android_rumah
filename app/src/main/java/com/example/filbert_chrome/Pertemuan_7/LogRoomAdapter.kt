package com.example.filbert_chrome.Pertemuan_7

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filbert_chrome.Data.entity.LogEntity
import com.example.filbert_chrome.databinding.ItemLogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LogRoomAdapter(
    private val logs: List<LogEntity>,
    private val onDelete: (LogEntity) -> Unit,
    private val onEdit: (LogEntity) -> Unit
) : RecyclerView.Adapter<LogRoomAdapter.LogViewHolder>() {

    inner class LogViewHolder(val binding: ItemLogBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val log = logs[position]
        holder.binding.tvTitle.text = log.title
        holder.binding.tvDescription.text = log.description
        holder.binding.tvCategory.text = log.category

        // Fitur DELETE (H)
        holder.binding.btnDeleteLog.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Log")
                .setMessage("Apakah Anda yakin ingin menghapus log ini?")
                .setPositiveButton("Hapus") { _, _ -> onDelete(log) }
                .setNegativeButton("Batal", null)
                .show()
        }

        // Fitur UPDATE (U) - Klik item untuk edit
        holder.itemView.setOnClickListener {
            onEdit(log)
        }
    }

    override fun getItemCount(): Int = logs.size
}
