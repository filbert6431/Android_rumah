package com.example.filbert_chrome.Pertemuan_7

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.filbert_chrome.Halaman_admin
import com.example.filbert_chrome.Pertemuan_3.data.Halaman_login
import com.example.filbert_chrome.R
import com.example.filbert_chrome.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class Home_fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // kita buat tambahin logikanya disini dengan
    // membuat Override baru di dalam onViewCreated
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)


        binding.btnwebAdmin.setOnClickListener {
            val intent = Intent(requireContext(), Halaman_admin::class.java)
            startActivity(intent)
        }

        val sharedPref = requireActivity().getSharedPreferences("user_pref", AppCompatActivity.MODE_PRIVATE)
        
        // Mengambil username yang disimpan saat login (key: "username")
        // Jika tersedia "registered_name" dari proses registrasi, kita tampilkan itu.
        val username = sharedPref.getString("username", "User")
        val namaTampil = sharedPref.getString("registered_name", username)
        
        binding.textView2.text = "Selamat Datang $namaTampil"

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()

                    sharedPref.edit().putBoolean("isLogin", false).apply()

                    val intent = Intent(requireContext(), Halaman_login::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()

                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT)
                        .show()
                }
                .show()
        }
    }

}