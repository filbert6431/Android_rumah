package com.example.filbert_chrome.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.filbert_chrome.Data.dao.LogDao
import com.example.filbert_chrome.Data.dao.PersilDao
import com.example.filbert_chrome.Data.entity.LogEntity
import com.example.filbert_chrome.Data.entity.PersilEntity

@Database(
    entities = [PersilEntity::class, LogEntity::class], // Menambahkan LogEntity
    version = 2, // Menaikkan versi karena ada perubahan skema
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun PersilDao(): PersilDao
    abstract fun LogDao(): LogDao // Menambahkan LogDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .fallbackToDestructiveMigration() // Menghapus data lama jika versi naik (untuk mempermudah saat dev)
                .build().also { INSTANCE = it }
            }
        }
    }
}