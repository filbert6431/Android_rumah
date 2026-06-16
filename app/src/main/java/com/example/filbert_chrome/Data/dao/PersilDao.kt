package com.example.filbert_chrome.Data.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.filbert_chrome.Data.entity.PersilEntity

@Dao
interface PersilDao {
    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<PersilEntity>

    @Insert
    suspend fun insert(note: PersilEntity)

    @Delete
    suspend fun delete(note: PersilEntity)
}
