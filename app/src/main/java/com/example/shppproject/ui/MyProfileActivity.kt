package com.example.shppproject.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shppproject.databinding.ActivityMyprofileBinding
import com.example.shppproject.utils.Parser

class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyprofileBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val (name, soname) = Parser().parseName(intent.getStringExtra("mail"))
        binding.userName.text = "$name $soname"
    }
}