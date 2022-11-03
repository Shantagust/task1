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
        initialRegistrationButton()
    }

    // try init data from SharedPreference or load default values
    private fun init() = with(binding) {
        variableInit()
        mail.setText(shPref.getString(USER_MAIL, null))
        password.setText(shPref.getString(USER_PASSWORD, null))
        savePwdCheckbox.isChecked = shPref.getBoolean(SAVED_STATUS, false)
    }

    private fun variableInit() {
        shPref = getSharedPreferences(APP_NAME, Context.MODE_PRIVATE)
        validator = Validators()
        storage = DataStorage()
    }

    private fun initialRegistrationButton() = with(binding) {
        btnRegister.setOnClickListener {
            checkAuth()
        }
    }

    private fun checkAuth() = with(binding) {
        if (validator.isMailCorrect(mail.text.toString())) {
            mailLayout.helperText = ""
            if (validator.isPasswordCorrect(password.text.toString())) {
                passwordLayout.helperText = ""
                openUserProfile()
            } else passwordLayout.helperText = getString(R.string.wrong_password)
        } else mailLayout.helperText = getString(R.string.wrong_mail)
    }

    private fun openUserProfile() = with(binding) {
        isSavedData()
        val dataIntent = Intent(this@AuthActivity, MyProfileActivity::class.java)
        dataIntent.putExtra(USER_MAIL, mail.text.toString())
        startActivity(dataIntent)
    }

    private fun isSavedData() {
        if (binding.savePwdCheckbox.isChecked) storage.saveAccount(shPref, binding)
        else storage.clearAccountData(shPref)
    }

}