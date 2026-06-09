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

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            // Berikan jeda splash screen sedikit lebih lama jika sistem berat
            delay(2500)

            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            if (isLogin) {
                // Jika sudah login, langsung ke base_activity
                startActivity(Intent(this@SplashScreenActivity, base_activity::class.java))
            } else {
                // Jika belum, ke halaman login
                startActivity(Intent(this@SplashScreenActivity, Halaman_login::class.java))
            }
            // HANYA panggil finish sekali di akhir navigasi
            finish()
        }
    }
}