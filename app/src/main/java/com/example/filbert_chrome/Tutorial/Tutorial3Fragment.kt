package com.example.filbert_chrome.Tutorial

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filbert_chrome.Pertemuan_3.data.Halaman_login
import com.example.filbert_chrome.R
import com.example.filbert_chrome.databinding.FragmentTutorial3Binding

class Tutorial3Fragment : Fragment() {

    private var _binding: FragmentTutorial3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorial3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMulai.setOnClickListener {
            // Berpindah ke Halaman Login
            val intent = Intent(requireContext(), Halaman_login::class.java)
            startActivity(intent)
            // Menutup activity tutorial agar tidak bisa kembali ke onboarding saat tekan back
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}