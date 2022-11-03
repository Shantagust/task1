package com.example.shppproject.utils

class Validators {

    fun isMailCorrect(mail: String): Boolean {
        return (android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches() &&
                mail.split("@").first().contains("."))
    }

    fun isPasswordCorrect(password: String): Boolean {
        return (password.contains('@') or password.contains("$"))
    }

}