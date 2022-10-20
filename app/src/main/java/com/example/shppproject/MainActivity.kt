package com.example.shppproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shppproject.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    // binding variable for current activity (just initialized, but empty)
    lateinit var binding: MainActivityBinding

    // main func which start when create activity, and create all View objects on activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // fill binding variable
        binding = MainActivityBinding.inflate(layoutInflater)

        // create all view objects for current activity
        setContentView(binding.root)

        // change default user name in profile to parsed name from mail
        binding.userName.text = intent.getStringExtra("name")
    }

}