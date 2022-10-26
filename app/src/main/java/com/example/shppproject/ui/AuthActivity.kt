package com.example.shppproject.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shppproject.R
import com.example.shppproject.databinding.ActivityAuthBinding
import com.example.shppproject.utils.DataStorage
import com.example.shppproject.utils.Validators

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    lateinit var shPref: SharedPreferences
    lateinit var validator: Validators
    lateinit var dataIntent: Intent
    lateinit var storage: DataStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        checkAuth()
    }

    // try init data from SharedPreference or load default values
    private fun init() {
        shPref = getSharedPreferences("myApp", Context.MODE_PRIVATE)
        validator = Validators()
        storage = DataStorage()
        binding.mail.setText(shPref.getString("mail", null))
        binding.password.setText(shPref.getString("password", null))
        binding.savePwdCheckbox.isChecked = shPref.getBoolean("saved", false)
        dataIntent = Intent(this, MainActivity::class.java)
    }

    private fun checkAuth() {
        binding.btnRegister.setOnClickListener {
            if (validator.isMailCorrect(binding.mail.text.toString())) {
                binding.mailLayout.helperText = ""
                if (validator.isPasswordCorrect(binding.password.text.toString())) {
                    binding.passwordLayout.helperText
                    dataIntent.putExtra("mail", binding.mail.text.toString())
                    isCheckedCheckbox()
                    startActivity(dataIntent)
                } else {
                    binding.passwordLayout.helperText = resources.getString(R.string.wrong_password)
                }
            } else binding.mailLayout.helperText = resources.getString(R.string.wrong_mail)
        }
    }

    private fun isCheckedCheckbox() {
        if (binding.savePwdCheckbox.isChecked) {
            storage.saveAccount(shPref, binding)
        } else {
            storage.clearAccountData(shPref)
        }
    }

}