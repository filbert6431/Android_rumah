package com.example.filbert_chrome.Data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class PersilEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val nomor_objek: String,
        val nama_Pemilik: String,
        val luas_tanah: String,
        val alamat: String,
        val jenis_tanah: String
)