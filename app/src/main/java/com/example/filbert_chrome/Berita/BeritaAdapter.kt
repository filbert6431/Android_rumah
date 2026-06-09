package com.example.filbert_chrome.Berita

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filbert_chrome.Data.Model.BeritaModel
import com.example.filbert_chrome.R

// 1. PASTIKAN menggunakan 'var' dan 'MutableList' di sini
class BeritaAdapter(private var listBerita: MutableList<BeritaModel>) :
    RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    inner class BeritaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.imgPhoto)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_berita, parent, false)
        return BeritaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = listBerita[position]
        holder.tvTitle.text = berita.title
        holder.tvDescription.text = berita.description

        Glide.with(holder.itemView.context)
            .load(berita.image)
            .placeholder(R.drawable.filbert)
            .error(R.drawable.filbert)
            .into(holder.imgPhoto)
    }

    override fun getItemCount(): Int = listBerita.size

    // 2. PASTIKAN FUNGSI INI DITULIS DI DALAM CLASS ADAPTER (bukan di luar kurung kurawal class)
    fun updateData(newBerita: List<BeritaModel>) {
        this.listBerita.clear()
        this.listBerita.addAll(newBerita)
        notifyDataSetChanged()
    }
}