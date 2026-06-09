package com.example.filbert_chrome.Data.API

import com.example.filbert_chrome.Data.Model.BeritaResponse
import retrofit2.http.GET
import retrofit2.http.Query

// D:/punya yg yg/kodingan/Mobile/Project/Rumah/Filbert_Chrome/app/src/main/java/com/example/filbert_chrome/Data/API/BeritaApiService.kt

interface BeritaApiService {
    @GET("top-headlines")
    suspend fun getBerita(
        @Query("category") category: String = "general",
        @Query("lang") language: String = "id",
        @Query("apikey") apiKey: String = "d440bea70c373255bf4b4a72ef276098"
    ): BeritaResponse
}
