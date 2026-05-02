// D:/punya yg yg/kodingan/Mobile/Project/Rumah/Filbert_Chrome/app/src/main/java/com/example/filbert_chrome/SplashScreenActivity.kt

package com.example.filbert_chrome

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.filbert_chrome.Pertemuan_3.data.Halaman_login
import com.example.filbert_chrome.Pertemuan_7.Home_fragment
// Hapus import Home_fragment jika tidak diperlukan lagi di sini
import com.example.filbert_chrome.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            // PERBAIKAN: Arahkan ke base_activity, bukan Home_fragment
            val username = sharedPref.getString("username", "User")
            val intent = Intent(this, base_activity::class.java)
            startActivity(intent)
            finish()

        }
            // Menggunakan else agar coroutine tidak berjalan jika user sudah login
            lifecycleScope.launch {
                delay(2000)
                val intent = Intent(this@SplashScreenActivity, Halaman_login::class.java)
                startActivity(intent)
                finish()

        }
    }
}