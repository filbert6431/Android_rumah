package com.example.filbert_chrome.Data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logs")
data class LogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val category: String, // Misal: "Persil", "Sistem", "Sengketa"
    val timestamp: String
)
