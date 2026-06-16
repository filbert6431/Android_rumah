package com.example.filbert_chrome.Pertemuan_7

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filbert_chrome.Pertemuan_3.data.Halaman_login
import com.example.filbert_chrome.databinding.FragmentProfilBinding

class Profil_fragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Menggunakan ViewBinding agar lebih mudah memanggil ID button
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Logika Tombol Logout
        binding.btnLogout.setOnClickListener {
            // 1. Akses SharedPreferences yang sama dengan Halaman_login
            val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            // 2. Set isLogin menjadi false untuk keluar dari sesi
            editor.putBoolean("isLogin", false)
            editor.apply()

            // 3. Pindah ke Halaman Login dan bersihkan tumpukan halaman (Clear Task)
            // Agar user tidak bisa menekan tombol 'Back' untuk kembali ke Profile
            val intent = Intent(requireContext(), Halaman_login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
