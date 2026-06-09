package com.example.filbert_chrome.Data.Model

import com.google.gson.annotations.SerializedName

data class BeritaModel(
    @SerializedName("image")
    val image: String?, // Boleh null jika berita tidak ada gambar
    
    @SerializedName("title")
    val title: String?,
    
    @SerializedName("description")
    val description: String?,

    @SerializedName("url")
    val url: String?
)
