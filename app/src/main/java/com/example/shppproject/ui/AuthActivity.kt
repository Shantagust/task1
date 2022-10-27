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

        with(binding) {
            mail.setText(shPref.getString("mail", null))
            password.setText(shPref.getString("password", null))
            savePwdCheckbox.isChecked = shPref.getBoolean("saved", false)
        }
    }

    private fun checkAuth() {
        val dataIntent = Intent(this, MainActivity::class.java)

        with(binding) {
            btnRegister.setOnClickListener {
                if (validator.isMailCorrect(mail.text.toString())) {
                    mailLayout.helperText = ""
                    if (validator.isPasswordCorrect(password.text.toString())) {
                        passwordLayout.helperText = ""

                        dataIntent.putExtra("mail", mail.text.toString())
                        isCheckedCheckbox()
                        startActivity(dataIntent)
                    } else passwordLayout.helperText = resources.getString(R.string.wrong_password)
                } else mailLayout.helperText = resources.getString(R.string.wrong_mail)
            }
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