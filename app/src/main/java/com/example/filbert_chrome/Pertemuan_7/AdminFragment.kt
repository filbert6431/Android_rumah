package com.example.filbert_chrome.Pertemuan_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filbert_chrome.Data.AppDatabase
import com.example.filbert_chrome.Data.entity.LogEntity
import com.example.filbert_chrome.databinding.FragmentLogAdminBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class AdminFragment : Fragment() {
    private var _binding: FragmentLogAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLogAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchLogs()
    }

    private fun setupRecyclerView() {
        binding.rvAdmin.layoutManager = LinearLayoutManager(context)
    }

    private fun fetchLogs() {
        lifecycleScope.launch {
            val db = AppDatabase.getInstance(requireContext())
            val logs = db.LogDao().getLogsByCategory("Admin")
            
            binding.rvAdmin.adapter = LogRoomAdapter(
                logs = logs,
                onDelete = { log -> deleteLog(log) },
                onEdit = { log -> showEditDialog(log) }
            )
        }
    }

    private fun deleteLog(log: LogEntity) {
        lifecycleScope.launch {
            AppDatabase.getInstance(requireContext()).LogDao().deleteLog(log)
            fetchLogs()
            Toast.makeText(context, "Log dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showEditDialog(log: LogEntity) {
        val context = requireContext()
        val builder = MaterialAlertDialogBuilder(context)
        builder.setTitle("Edit Log Admin")

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(40, 20, 40, 20)

        val inputTitle = EditText(context)
        inputTitle.setText(log.title)
        inputTitle.hint = "Judul Log"
        layout.addView(inputTitle)

        val inputDesc = EditText(context)
        inputDesc.setText(log.description)
        inputDesc.hint = "Deskripsi"
        layout.addView(inputDesc)

        builder.setView(layout)

        builder.setPositiveButton("Update") { _, _ ->
            val newTitle = inputTitle.text.toString()
            val newDesc = inputDesc.text.toString()
            if (newTitle.isNotEmpty() && newDesc.isNotEmpty()) {
                updateLog(log.copy(title = newTitle, description = newDesc))
            }
        }
        builder.setNegativeButton("Batal", null)
        builder.show()
    }

    private fun updateLog(log: LogEntity) {
        lifecycleScope.launch {
            AppDatabase.getInstance(requireContext()).LogDao().updateLog(log)
            fetchLogs()
            Toast.makeText(context, "Log diperbarui", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchLogs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
