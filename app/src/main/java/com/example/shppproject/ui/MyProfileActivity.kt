package com.example.shppproject.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shppproject.R
import com.example.shppproject.databinding.ActivityMyprofileBinding
import com.example.shppproject.utils.Constants
import com.example.shppproject.utils.Parser

class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyprofileBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set name in user profile
        val filledMail: String? = intent.getStringExtra(Constants.USER_MAIL)
        filledMail?.let { mail ->
            val (name, lastname) = Parser().parseName(mail.split("@").first())
            binding.userName.text = getString(R.string.user_name_placeholder, name, lastname)
        }
    }
}