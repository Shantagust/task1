package com.example.shppproject.utils

import android.content.SharedPreferences
import com.example.shppproject.databinding.ActivityAuthBinding

class DataStorage {

    // this functions saved data from mail and password fields to SharedPreferences store
    fun saveAccount(shPref: SharedPreferences, binding: ActivityAuthBinding) {
        val editor: SharedPreferences.Editor = shPref.edit()
        editor.putString("mail", binding.mail.text.toString())
        editor.putString("password", binding.password.text.toString())
        editor.putBoolean("saved", binding.savePwdCheckbox.isChecked)
        editor.apply()
    }

    fun clearAccountData(shPref: SharedPreferences) {
        val editor: SharedPreferences.Editor = shPref.edit()
        editor.clear()
        editor.apply()
    }
}