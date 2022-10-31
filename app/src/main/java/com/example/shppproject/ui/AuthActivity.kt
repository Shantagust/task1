package com.example.shppproject.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shppproject.R
import com.example.shppproject.databinding.ActivityAuthBinding
import com.example.shppproject.utils.Constants.APP_NAME
import com.example.shppproject.utils.Constants.SAVED_STATUS
import com.example.shppproject.utils.Constants.USER_MAIL
import com.example.shppproject.utils.Constants.USER_PASSWORD
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
        shPref = getSharedPreferences(APP_NAME, Context.MODE_PRIVATE)
        validator = Validators()
        storage = DataStorage()

        with(binding) {
            mail.setText(shPref.getString(USER_MAIL, null))
            password.setText(shPref.getString(USER_PASSWORD, null))
            savePwdCheckbox.isChecked = shPref.getBoolean(SAVED_STATUS, false)
        }
    }

    private fun checkAuth() {
        val dataIntent = Intent(this, MyProfileActivity::class.java)

        with(binding) {
            btnRegister.setOnClickListener {
                if (validator.isMailCorrect(mail.text.toString())) {
                    mailLayout.helperText = ""
                    if (validator.isPasswordCorrect(password.text.toString())) {
                        passwordLayout.helperText = ""

                        dataIntent.putExtra(USER_MAIL, mail.text.toString())
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