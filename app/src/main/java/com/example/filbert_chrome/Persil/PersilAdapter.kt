package com.example.filbert_chrome.Persil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filbert_chrome.Data.entity.PersilEntity
import com.example.filbert_chrome.databinding.ItemPersilBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class PersilAdapter(
    private val persilList: List<PersilEntity>,
    private val onDeleteClick: (PersilEntity) -> Unit
) : RecyclerView.Adapter<PersilAdapter.PersilViewHolder>() {

    inner class PersilViewHolder(val binding: ItemPersilBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersilViewHolder {
        val binding = ItemPersilBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersilViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersilViewHolder, position: Int) {
        val item = persilList[position]

        holder.binding.tvTitle.text = "NOP: ${item.nomor_objek}"
        holder.binding.tvContent.text = "Pemilik: ${item.nama_Pemilik}\nLuas: ${item.luas_tanah} m²"

        holder.itemView.setOnClickListener {
            Snackbar.make(holder.itemView, "Persil milik ${item.nama_Pemilik}", Snackbar.LENGTH_SHORT).show()
        }

        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Data Persil")
                .setMessage("Apakah kamu yakin ingin menghapus data NOP ${item.nomor_objek}?")
                .setPositiveButton("Ya") { dialog, _ ->
                    onDeleteClick(item)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int = persilList.size
}
