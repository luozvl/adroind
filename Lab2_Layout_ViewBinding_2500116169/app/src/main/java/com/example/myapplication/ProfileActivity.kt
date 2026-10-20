package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận email được truyền từ LoginActivity hiển thị lên giao diện
        binding.tvValueEmail.text = intent.getStringExtra(EXTRA_EMAIL).orEmpty()

        // Nút đăng xuất quay lại màn hình Login
        binding.btnLogout.setOnClickListener { finish() }
    }

    companion object {
        const val EXTRA_EMAIL = "extra_email"
    }
}