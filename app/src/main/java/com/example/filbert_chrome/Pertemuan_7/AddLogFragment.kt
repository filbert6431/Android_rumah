package com.example.filbert_chrome.Pertemuan_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.filbert_chrome.Data.AppDatabase
import com.example.filbert_chrome.Data.entity.LogEntity
import com.example.filbert_chrome.databinding.FragmentAddLogBinding
import com.example.filbert_chrome.R
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddLogFragment : DialogFragment() {
    private var _binding: FragmentAddLogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Spinner dengan layout custom agar teks berwarna putih dan background gelap
        val categories = arrayOf("Sengketa", "Persil", "Sistem", "Admin")
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item_white, categories)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_white)
        binding.spinnerCategory.adapter = adapter

        binding.btnSave.setOnClickListener {
            saveLog()
        }
    }

    private fun saveLog() {
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()
        val category = binding.spinnerCategory.selectedItem.toString()

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(requireContext(), "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val newLog = LogEntity(
            title = title,
            description = description,
            category = category,
            timestamp = timestamp
        )

        lifecycleScope.launch {
            AppDatabase.getInstance(requireContext()).LogDao().insertLog(newLog)
            Toast.makeText(requireContext(), "Log berhasil disimpan", Toast.LENGTH_SHORT).show()
            
            // Notify activity to refresh current fragment if needed
            (activity as? LogActivity)?.refreshCurrentFragment()
            
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "AddLogFragment"
    }
}
