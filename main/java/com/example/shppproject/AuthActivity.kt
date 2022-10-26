package com.example.shppproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.shppproject.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    lateinit var shPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // fill variable binding_auth by elements from current activity
        binding = ActivityAuthBinding.inflate(layoutInflater)

        // set xml template for current activity
        setContentView(binding.root)
        init()
    }

    // try init data from SharedPreference or load default values
    private fun init() {
        shPref = getSharedPreferences("myApp", Context.MODE_PRIVATE)
        val mail: String? = shPref.getString("mail", null)
        val password: String? = shPref.getString("password", null)
        val saved: Boolean = shPref.getBoolean("saved", false)

        binding.registerBtn.setOnClickListener {
        }
        binding.mail.setText(mail)
        binding.password.setText(password)
        binding.savePwdCheckbox.isChecked = saved
    }

    // Check is correct mail or not (checking by '@' char)
    private fun isMail(mail_text: String): Boolean {
        return mail_text.contains('@')
    }

    // parse user name from user mail
    private fun parseName(data: Intent) {
        // get full user name (unparsed)
        val full_name = binding.mail.text!!.split("@").first().toString().split(".")
        data.putExtra("name", "${full_name[0].capitalize()} ${full_name[1].capitalize()}")
    }

    // listener for clicking of "registration" button in auth activity
    fun onClickRegistration(view: View) {

        // check correctly input mail (with @ in text)
        if (isMail(binding.mail.text.toString())) {

            // remove info about wrong mail
            binding.mailLayout.helperText = ""

            // make intent variable
            val data = Intent(this, MainActivity::class.java)
            // this method parse name from mail and put it in data (intent)
            parseName(data)
            // if checkbox is checked, save mail, pass, and checkbox state else - remove all data
            if (binding.savePwdCheckbox.isChecked) {
                saveAccount()
            } else {
                clearAccountData()
            }

            // open new activity with intent
            startActivity(data)

        } else {
            binding.mailLayout.helperText = "Wrong mail, check if the email address is " +
                    "entered correctly !"
        }
    }

    // this functions saved data from mail and password fields to SharedPreferences store
    private fun saveAccount() {
        shPref = getSharedPreferences("myApp", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = shPref.edit()
        editor.putString("mail", binding.mail.text.toString())
        editor.putString("password", binding.password.text.toString())
        editor.putBoolean("saved", binding.savePwdCheckbox.isChecked)
        editor.apply()
    }

    private fun clearAccountData() {
        shPref = getSharedPreferences("myApp", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = shPref.edit()
        editor.clear()
        editor.apply()
    }
}