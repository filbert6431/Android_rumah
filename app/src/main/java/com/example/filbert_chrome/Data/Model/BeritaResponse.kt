package com.example.filbert_chrome.Data.Model

import com.google.gson.annotations.SerializedName

data class BeritaResponse(
    @SerializedName("totalArticles")
    val totalArticles: Int,

    @SerializedName("articles")
    val articles: List<BeritaModel> // <-- PASTIKAN DI SINI MENGGUNAKAN List<BeritaModel>
)