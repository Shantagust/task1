package com.example.shppproject.utils

import android.content.SharedPreferences
import com.example.shppproject.databinding.ActivityAuthBinding

class DataStorage {

    // this functions saved data from mail and password fields to SharedPreferences store
    fun saveAccount(shPref: SharedPreferences, binding: ActivityAuthBinding) = with(binding) {
        val editor: SharedPreferences.Editor = shPref.edit()
        editor.putString(Constants.USER_MAIL, mail.text.toString())
        editor.putString(Constants.USER_PASSWORD, password.text.toString())
        editor.putBoolean(Constants.SAVED_STATUS, savePwdCheckbox.isChecked)
        editor.apply()
    }

    fun clearAccountData(shPref: SharedPreferences) {
        val editor: SharedPreferences.Editor = shPref.edit()
        editor.clear()
        editor.apply()
    }
}