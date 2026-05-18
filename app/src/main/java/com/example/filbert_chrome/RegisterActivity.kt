package com.example.filbert_chrome

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.example.filbert_chrome.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            val genderId = binding.rgGender.checkedRadioButtonId
            val agama = binding.spAgama.selectedItem.toString()

            var errorMessage: String? = null

            if (nama.isEmpty()) {
                errorMessage = "Nama tidak boleh kosong"
            } else if (username.isEmpty()) {
                errorMessage = "Username tidak boleh kosong"
            } else if (password.isEmpty()) {
                errorMessage = "Password tidak boleh kosong"
            } else if (confirmPassword.isEmpty()) {
                errorMessage = "Konfirmasi Password tidak boleh kosong"
            } else if (password != confirmPassword) {
                errorMessage = "Password dan Confirm Password harus sama"
            } else if (genderId == -1) {
                errorMessage = "Pilih jenis kelamin"
            }

            if (errorMessage != null) {
                Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT).show()
            } else {
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("registered_name", nama)
                editor.putString("registered_username", username)
                editor.putString("registered_password", password)
                editor.putString("registered_gender", if (genderId == R.id.rbLaki) "Laki-laki" else "Perempuan")
                editor.putString("registered_agama", agama)

                // Simpan tanggal dari DatePicker
                val day = binding.datePicker.dayOfMonth
                val month = binding.datePicker.month + 1
                val year = binding.datePicker.year
                editor.putString("registered_dob", "$day/$month/$year")

                editor.apply()

                // Selesai registrasi, kembali ke login atau tutup activity
                finish()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.agama_list,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spAgama.adapter = adapter

    }
}